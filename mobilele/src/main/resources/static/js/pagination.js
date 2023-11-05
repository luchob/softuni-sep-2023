function addSearchMask() {

  const searchParams = new URLSearchParams(window.location.search);

  searchParams.delete('page');
  searchParams.delete('size');

  var allLinks = document.getElementsByClassName('pagination-link');

  for (let link of allLinks) {
    let hrefParams = new URLSearchParams(link.search);

    const combined = new URLSearchParams({
      ...Object.fromEntries(hrefParams),
      ...Object.fromEntries(searchParams),
    })

    link.href = combined.toString();

  }

}