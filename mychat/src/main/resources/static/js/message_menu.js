let containerDiv = document.getElementsByClassName("chat_container");

async function fetchChats(){
console.log("Fetching user Details");
await fetch("/message_menu/fetch_chat_users")
.then(response => response.json())
.then(data =>{
    let chatUserList = data;
    console.log(data);
    chatUserList.sort((a,b)=> b.messageCount - a.messageCount);
    chatUserList.forEach(chatUser =>{
        console.log(chatUser);
        //console.log("before calling method");
        addUserToChatContainer(chatUser);
        //console.log("after calling method");
    });
}).catch(err => console.log("Error fetching chatUsers : "+err));
}
async function doStuff(){
    await fetchChats();
    addingEventListeners();
}
doStuff();

document.getElementById("user_verification_button").addEventListener("click", event => {
        //const enteredUserDiv = document.getElementById("entered_user");
        //const enteredUserName = enteredUserDiv.innerText.trim();
        let param = document.getElementById("entered_user").value;
        if(!param) return;
        //let param = new URLSearchParams({name:enteredUserName});
        console.log(param);
        fetch(`/user/verify?user=${param}`)
        .then(response => response.json())
        .then(data => {
            //System.out.println(data);
            console.log(data);
            if(data.data.chatUserId==null){
                document.getElementById("user_verification_message").textContent = "User not found.";
                return;
            }else{
                document.getElementById("user_verification_message").textContent = "Verified";
            }
            addUserToChatContainer(data.data);
            console.log("Calling event listener method");
            addingEventListeners();
            console.log("EventListener added")
        }).catch(err => console.log("Error occurred while user verification"));

});

function addUserToChatContainer(chatUser){
               let data = chatUser;
               let newChatDiv = document.createElement("div");
               newChatDiv.className = "chat_div"

               let chatUserIdDiv = document.createElement("div");
               let chatUserNameDiv = document.createElement("div");
               let messageCountDiv = document.createElement("div");

               chatUserIdDiv.className = "chat_user_id";
               chatUserNameDiv.className = "chat_user_name";
               messageCountDiv.className = "message_count";

               chatUserIdDiv.className = "chat_user_id";
               chatUserNameDiv.className = "chat_user_name";
               messageCountDiv.className = "message_count";

               chatUserIdDiv.textContent = data.chatUserId;
               chatUserNameDiv.textContent = data.chatUserName;
               messageCountDiv.textContent = data.messageCount;

               if(chatUser.messageCount==0){
                    messageCountDiv.hidden = true;
               }
               chatUserIdDiv.hidden = true;

               newChatDiv.appendChild(chatUserIdDiv);
               newChatDiv.appendChild(chatUserNameDiv);
               newChatDiv.appendChild(messageCountDiv);

               containerDiv[0].appendChild(newChatDiv);

}
function addingEventListeners(){
console.log("inside adding event listener method");
document.querySelectorAll(".chat_div").forEach( div => {
    div.addEventListener("click",event => {
        let clickedDiv = event.target;
        let chatUserIdDiv = clickedDiv.querySelector(".chat_user_id");
        let clickedChatUserId = Number(chatUserIdDiv.textContent);
        let clickedChatUserNameDiv = clickedDiv.querySelector(".chat_user_name");
        let clickedChatUserName = clickedChatUserNameDiv.textContent;
        console.log(clickedChatUserId);
        console.log(clickedChatUserName);
        sessionStorage.setItem("chatUserId",clickedChatUserId);
        sessionStorage.setItem("chatUserName",clickedChatUserName);
        window.location.href = "/message/chat";
    });
});
}

