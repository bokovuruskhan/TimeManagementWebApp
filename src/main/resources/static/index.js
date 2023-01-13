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
function clockTimer()
{
  var date = new Date();

  var time = [date.getHours(),date.getMinutes(),date.getSeconds()];
  var days = date.getDay();

  if(time[0] < 10){time[0] = "0"+ time[0];}
  if(time[1] < 10){time[1] = "0"+ time[1];}
  if(time[2] < 10){time[2] = "0"+ time[2];}

  var currentTime = date.getDate() + "." + date.getMonth() + 1+ "."+ date.getFullYear() +" "+ [time[0],time[1],time[2]].join(':');
  var clock = document.getElementById("clock");
  console.log(currentTime);
  clock.innerHTML = currentTime;
  setTimeout("clockTimer()", 1000);
};