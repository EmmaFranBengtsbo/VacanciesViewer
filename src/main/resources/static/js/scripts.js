function load(){
    let xmlObjects = Get("/vacancy").responseXML;
    let countOfNodes = xmlObjects.querySelector('Vacancies').childElementCount;
    if (countOfNodes > 0) {
        let siblings = xmlObjects.querySelectorAll('Vacancies > vacancy');
        for (let i = 0; i < countOfNodes; i++) {
            let id = siblings[i].querySelector('vacancy > id').textContent;
            let name = siblings[i].querySelector('vacancy > name').textContent;
            let salary = siblings[i].querySelector('vacancy > salary').textContent;
            let experience = siblings[i].querySelector('vacancy > experience').textContent;
            let city = siblings[i].querySelector('vacancy > city').textContent;

            let body = $('#tableOfVacancies > tbody');
            let tr = $('<tr>');
            tr.attr('id', 'row');
            let td1 = $('<td>');
            td1.text(id);
            tr.append(td1);
            let td2 = $('<td>');
            td2.text(name);
            tr.append(td2);
            let td3 = $('<td>');
            if (salary !== "0") {
                td3.text(salary);
            }
            tr.append(td3);
            let td4 = $('<td>');
            td4.text(experience);
            tr.append(td4);
            let td5 = $('<td>');
            td5.text(city);
            tr.append(td5);
            let deleteButton = document.createElement("button");
            deleteButton.setAttribute("type", "button");
            deleteButton.setAttribute("class", "btn btn-danger btn-sm");
            deleteButton.appendChild(document.createTextNode("Удалить"));
            deleteButton.addEventListener("click", function () {
                processDelete("/vacancy", id)
            });
            let td6 = document.createElement("td");
            td6.append(deleteButton);
            tr.append(td6);
            body.append(tr);
        }
    }
}

function Get(requestUrl) {
    let Httpreq = new XMLHttpRequest();
    Httpreq.open("GET", requestUrl, false);
    Httpreq.send(null);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to GET " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found GET " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function Post(requestUrl, body) {
    let Httpreq = new XMLHttpRequest();
    Httpreq.open("POST", requestUrl, false);
    Httpreq.setRequestHeader("Content-type", "application/xml");
    Httpreq.send(body);
    return Httpreq;
}

function Delete(requestUrl) {
    let Httpreq = new XMLHttpRequest();
    Httpreq.open("DELETE", requestUrl, false);
    Httpreq.send(null);
    if (Httpreq.status === 400) {
        $('#error-text').text("Bad request to DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    if (Httpreq.status === 404) {
        $('#error-text').text("Not found DELETE " + requestUrl);
        $('#myModal').modal('show');
    }
    return Httpreq;
}

function searchIdClick() {
    let idInput = $('#searchId');
    let id =  idInput.val();
    console.log(id);
    if (id.includes("e") || id === "") {
        $('#error-text').text('Некорректный id');
        idInput.val("");
        $('#myModal').modal('show');
    } else {
        showButtonClick("/vacancy", id);
        idInput.val("");
    }
}

function showButtonClick(root, id) {
    let requestToShow = Get(root + "/" + id);
    if (requestToShow.status === 200) {
        let xmlObjectToShow = requestToShow.responseXML;
        let idVacancy = xmlObjectToShow.querySelector('vacancy > id').textContent;
        let name = xmlObjectToShow.querySelector('vacancy > name').textContent;
        let salary = xmlObjectToShow.querySelector('vacancy > salary').textContent;
        let experience = xmlObjectToShow.querySelector('vacancy > experience').textContent;
        let city = xmlObjectToShow.querySelector('vacancy > city').textContent;
        $('#showModal').modal('show');
        $('#existId').val(idVacancy);
        $('#existName').val(name);
        $('#existSalary').val(salary);
        $('#existExperience').val(experience);
        $('#existCity').val(city);
    }
}

function clickCreate() {
    document.getElementById("newName").value = "";
    document.getElementById("newSalary").value = "";
    document.getElementById("newExperience").value = "";
    document.getElementById("newCity").value = "";
    $('#createModal').modal('show');
}

function processCreate(root) {
    let name = document.getElementById("newName").value;
    let salary = document.getElementById("newSalary").value;
    let experience = document.getElementById("newExperience").value;
    let city = document.getElementById("newCity").value;
    let body = "<vacancy>"
        +"<id></id>"
        +"<name>" + name + "</name>"
        +"<salary>" + salary + "</salary>"
        +"<experience>"+ experience + "</experience>"
        +"<city>" + city + "</city>"
        +"</vacancy>";
    let response = Post(root, body);
    if (response.status === 200 || response.status === 201)  {
        $('#createModal').modal('hide');
        $('table#tableOfVacancies > tbody').remove();
        let tbody = $('<tbody>');
        $('table#tableOfVacancies').append(tbody);
        load();
    }
    if (response.status === 400) {
        let errorMessage = response.responseXML;
        let message = errorMessage.querySelector('error > message').textContent;
        $('#createModal').modal('hide');
        $('#error-text').text(message);
        $('#myModal').modal('show');
    }
    if (response.status === 404) {
        $('#createModal').modal('hide');
        $('#error-text').text("Not found POST " + requestUrl);
        $('#myModal').modal('show');
    }
}

function processDelete(root, id) {
    Delete(root + "/" + id);
    $('table#tableOfVacancies > tbody').remove();
    let tbody = $('<tbody>');
    $('table#tableOfVacancies').append(tbody);
    load();
}