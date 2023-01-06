function refreshPage(){
    document.location.reload();
};
function post(url,jsonData) {
    var xhr = new XMLHttpRequest();
    var json = JSON.stringify(jsonData);
    xhr.open("POST", url, true)
    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
    xhr.onreadystatechange = refreshPage();
    xhr.send();
};

function addTask(){
    const data = new FormData(event.target);
    const jsonData = Object.fromEntries(data.entries());
    post("/task",jsonData);
};