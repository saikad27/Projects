
//Defining event handler for button click
document.getElementById("send_button").addEventListener("click",() => {

    //Extracting entered message
    const messageElement = document.getElementById("input_message");
    const message = messageElement.innerText.trim();

    //Storing div element for later appending sent message
    const sentMessageDiv = document.getElementById("messages");

    if(!message) return;    //No operation for empty message

    //Creating messageDTO for fetch request body
    const messageDTO = {
        receiverId : sessionStorage.getItem("userId"),
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
        const sentMessage = data.message;
        const messageDiv = document.createElement("div");
        messageDiv.textContent = sentMessage;
        sentMessageDiv.appendChild(messageDiv);
        messageElement.innerText = '';

    }).catch(err => console.error("Error sending message:",err));
});