let dataMap = new Map();

fetch(`/api/v1/models/all`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json()
    })
    .then(data => {
        data.forEach(o => {
            dataMap.set(o.name, []);
            o.models.forEach(m => dataMap.get(o.name).push(m.name));
        })
    })
    .catch(error => console.log(error));

function populateModelsData() {
    let brandInputValue = document.getElementById('brandId').value;
    if (dataMap.get(brandInputValue) !== undefined) {
        let modelDatalist = document.getElementById('vehicleModels');
        let modelOptions = [];
        for (let m of dataMap.get(brandInputValue)) {
            let option = document.createElement('option');
            option.setAttribute('value', m);
            modelOptions.push(option);
        }

        modelDatalist.replaceChildren(...modelOptions)
    }
}

document.getElementById('brandId').addEventListener('focusout', populateModelsData);