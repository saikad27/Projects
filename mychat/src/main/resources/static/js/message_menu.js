let containerDiv = document.getElementById("chat_container");
for(let num=1;num<=10;num++){
    let newChatDiv = document.createElement("div");
    newChatDiv.classList.add("chat_box");
    newChatDiv.textContent = "User "+num;
   containerDiv.appendChild(newChatDiv);
}