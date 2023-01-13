function refreshPage(){
    document.location.reload();
};
function sendRequest(url,method,jsonData,refreshFlag) {
    var xhr = new XMLHttpRequest();
    var json = JSON.stringify(jsonData);
    console.log(json);
    console.log(method);
    xhr.open(method, url, true)
    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
    xhr.onreadystatechange = () => {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        const status = xhr.status;
        if (status === 0 || (status >= 200 && status < 400)) {
            if(refreshFlag){
                refreshPage();
            }
        }
      }
    };
    xhr.send(json);
};
function addNodeToTask(){
    const data = new FormData(event.target);
    const jsonData = Object.fromEntries(data.entries());
    sendRequest("/task/note","POST",jsonData,false);
};
function deleteTask(){
    const data = new FormData(event.target);
    const jsonData = Object.fromEntries(data.entries());
    sendRequest("/task","DELETE",jsonData,true);
};
function addTask(){
    const data = new FormData(event.target);
    const jsonData = Object.fromEntries(data.entries());
    sendRequest("/task","POST",jsonData,true);
};