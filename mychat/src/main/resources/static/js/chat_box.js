let messageClassDiv = document.getElementsByClassName("messages");

function fetchNMessages(){
    fetch("/message/fetch_messages?chatUserId="+sessionStorage.getItem("chatUserId"),{
        method:"POST"
      //  headers:{"content-type" : "application/json"},
       // body:JSON.stringify({chatUserId:sessionStorage.getItem("chatUserId")})
    })
    .then(response => response.json())
    .then(messageList => {
        console.log(messageList);
        messageList.forEach(message => {
        console.log(message);
        let messageDiv = document.createElement("div");
        if(message.senderId==sessionStorage.getItem("userId")){
               messageDiv.className = "sent_message";
        }else{
               messageDiv.className = "received_message";
        }
               messageDiv.textContent = message.message;
               messageClassDiv[0].appendChild(messageDiv);
        });
    }).catch(err => console.log("Error fetching messages : "+err));
}
fetchNMessages();

document.getElementById("exit_chat_button").addEventListener("click",() => {
    sessionStorage.setItem("chatUserId",null);
    sessionStorage.setItem("chatUserName",null);
    console.log("removing user form active chat");
    fetch("/remove_user",{method:"POST"})
    .then(response =>{
        console.log("User removed successfully");
        window.location.href = "/message_menu";
    }).catch(err => console.log("Error occured while removing the user from active chat registry"));
});


//Defining event handler for button click
document.getElementById("send_button").addEventListener("click",() => {

    //Extracting entered message
    const messageElement = document.getElementById("input_message");
    const message = messageElement.innerText.trim();

    //Storing div element for later appending sent message
    let messageClassDiv = document.getElementsByClassName("messages");

    if(!message) return;    //No operation for empty message

    //Creating messageDTO for fetch request body
    const messageDTO = {
        senderId : sessionStorage.getItem("userId"),
        receiverId : sessionStorage.getItem("chatUserId"),
        message : message
    };

    //Fetch request
    fetch('/message/send',{
    method:"POST",
    headers:{"content-type" : "application/json"},
    body:JSON.stringify(messageDTO)
    })
    .then(response => response.json())
    .then(data => {
        //Displaying retrieved data on the page
        console.log(data);
        const sentMessage = data.message;
        let newMessageDiv = document.createElement("div");
        newMessageDiv.classList.add("sent_message");
        newMessageDiv.textContent = sentMessage;
        messageClassDiv[0].appendChild(newMessageDiv);
        messageElement.innerText = '';
    }).catch(err => console.error("Error sending message:",err));
});

async function fetchMessages(){
fetch('/message/fetch',{
method:"POST",
headers:{"content-type" : "application/json"}
//body:JSON.stringify(messageDTO)
})
.then(response => {
    if(response.status==503){
        return null;
    }else{
        return response.json();
    }
})
.then(data => {
        if(data==null){
        }else{
       const receivedMessage = data.message;
       let newMessageDiv = document.createElement("div");
       newMessageDiv.classList.add("received_message");
       newMessageDiv.textContent = receivedMessage;
       messageClassDiv[0].appendChild(newMessageDiv);
       }
       console.log("Fetching message for user : "+sessionStorage.getItem("userName"));
       fetchMessages();
}).catch(err => console.log("Error fetching message:",err));
}
fetchMessages();