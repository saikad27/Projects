let containerDiv = document.getElementsByClassName("chat_container");

async function fetchChats(){
console.log("Fetching user Details");
fetch("/message_menu/fetch_chat_users")
.then(response => response.json())
.then(data =>{
    let chatUserList = data;
    chatUserList.sort((a,b)=> b.messageCount - a.messageCount);
    chatUserList.forEach(chatUser =>{
        console.log(chatUser);
        let newChatDiv = document.createElement("div");
        newChatDiv.classList.add("chat_box");

        let chatUserIdDiv = document.createElement("div");
        let chatUserNameDiv = document.createElement("div");
        let messageCountDiv = document.createElement("div");

        chatUserIdDiv.className = "chat_user_id";
        chatUserNameDiv.className = "chat_user_name";
        messageCountDiv.className = "message_count";

        chatUserIdDiv.textContent = chatUser.chatUserId;
        chatUserNameDiv.textContent = chatUser.chatUserName;
        messageCountDiv.textContent = chatUser.messageCount;

        if(chatUser.messageCount==0){
             messageCountDiv.hidden = true;
        }

        chatUserIdDiv.hidden = true;

        newChatDiv.appendChild(chatUserIdDiv);
        newChatDiv.appendChild(chatUserNameDiv);
        newChatDiv.appendChild(messageCountDiv);

        containerDiv[0].appendChild(newChatDiv);

    });
}).catch(err => console.log("Error fetching chatUsers : "+err));
}
fetchChats();
