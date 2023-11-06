package bg.softuni.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheableInvocationHandler implements InvocationHandler {

  private final Map<String, Object> cache = new ConcurrentHashMap<>();
  private final Object realObject;

  public CacheableInvocationHandler(Object realObject) {
    this.realObject = realObject;
  }

  @Override
  public Object invoke(
      Object proxy,
      Method method,
      Object[] args) throws Throwable {

    Cacheable cacheableAnnotation =
        realObject
            .getClass()
            .getMethod(method.getName(), method.getParameterTypes())
            .getAnnotation(Cacheable.class);

    if (cacheableAnnotation != null) {
      String cacheKey = cacheableAnnotation.value();
      if (cache.containsKey(cacheKey)) {
        return cache.get(cacheKey);
      } else {
        Object value = method.invoke(realObject, args);
        cache.put(cacheKey, value);
        return value;
      }
    } else {
      return method.invoke(realObject, args);
    }
  }
}
