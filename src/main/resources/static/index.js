var taskTimers = {};
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
function changePriority(){
    const data = new FormData(event.target);
    const jsonData = Object.fromEntries(data.entries());
    sendRequest("/task/priority/change","POST",jsonData,true);
};
function completeTask(){
    const data = new FormData(event.target);
    const jsonData = Object.fromEntries(data.entries());
    sendRequest("/task/complete","POST",jsonData,true);
};
function addTask(){
    const data = new FormData(event.target);
    const jsonData = Object.fromEntries(data.entries());
    sendRequest("/task","POST",jsonData,true);
};
function setElapsedTime(taskId,elapsedTime){
    const entries = new Map([
      ['id', taskId],
      ['elapsedTime', elapsedTime]
    ]);
    const jsonData = Object.fromEntries(entries);
    sendRequest("/task/time/elapsed","POST",jsonData,false);
};
function startTaskTimer(taskId){
    var timer = document.getElementById("task"+taskId+"Timer");
    var timeString = timer.innerHTML.split(':');
    var time = [parseInt(timeString[0]),parseInt(timeString[1]),parseInt(timeString[2])];
    time[2] += 1;

    if(time[2]>=60){
    time[2]=0;
    time[1]+=1;
    }
    if(time[1]>=60){
    time[1]=0;
    time[0]+=1;
    }

    if(time[0] < 10){time[0] = "0"+ time[0];}
    if(time[1] < 10){time[1] = "0"+ time[1];}
    if(time[2] < 10){time[2] = "0"+ time[2];}

    timeString = [time[0],time[1],time[2]].join(':');
    timer.innerHTML = timeString;

    if((time[2]%5)==0){
        setElapsedTime(taskId,timeString);
    }

    var startButton = document.getElementById("task"+taskId+"StartButton");
    var startButton = document.getElementById("task"+taskId+"StartButton");
    var stopButton = document.getElementById("task"+taskId+"StopButton");
    startButton.disabled = true;
    stopButton.disabled = false;
    timer = setTimeout("startTaskTimer("+taskId+")", 1000);
    taskTimers[taskId]=timer;
};
function stopTaskTimer(taskId){
    var timer = document.getElementById("task"+taskId+"Timer");
    var timeString = timer.innerHTML.split(':');
    var time = [parseInt(timeString[0]),parseInt(timeString[1]),parseInt(timeString[2])];
    time[2] += 1;

    if(time[2]>=60){
        time[2]=0;
        time[1]+=1;
    }
    if(time[1]>=60){
        time[1]=0;
        time[0]+=1;
    }
    if(time[0] < 10){time[0] = "0"+ time[0];}
    if(time[1] < 10){time[1] = "0"+ time[1];}
    if(time[2] < 10){time[2] = "0"+ time[2];}
    timeString = [time[0],time[1],time[2]].join(':');
    setElapsedTime(taskId,timeString);

    if((time[2]%5)==0){
        setElapsedTime(taskId,timeString);
    }

    var startButton = document.getElementById("task"+taskId+"StartButton");
    var stopButton = document.getElementById("task"+taskId+"StopButton");
    startButton.disabled = false;
    stopButton.disabled = true;

    clearTimeout(taskTimers[taskId]);
    delete taskTimers[taskId];
};
function clockTimer()
{
  var date = new Date();

  var time = [date.getHours(),date.getMinutes(),date.getSeconds()];

  if(time[0] < 10){time[0] = "0"+ time[0];}
  if(time[1] < 10){time[1] = "0"+ time[1];}
  if(time[2] < 10){time[2] = "0"+ time[2];}

  var currentTime = date.getDate() + "." + date.getMonth() + 1+ "."+ date.getFullYear() +" "+ [time[0],time[1],time[2]].join(':');
  var clock = document.getElementById("clock");
  clock.innerHTML = currentTime;
  setTimeout("clockTimer()", 1000);
};