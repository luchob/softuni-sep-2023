async function convert(selector) {

  let priceContainers = document.getElementsByClassName(selector);

  for (priceCtnr of priceContainers) {
    let searchParams = new URLSearchParams({
      target: 'EUR',
      amount: priceCtnr.textContent,
    })


    await fetch('http://localhost:8080/api/currency/convert?' + searchParams)
    .then(response => response.json())
    .then(json => {
      priceCtnr.textContent = priceCtnr.textContent + " (" + json.amount + " " + json.currency + ")"
    })
  }




}