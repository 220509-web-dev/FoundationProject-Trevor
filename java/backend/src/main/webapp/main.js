window.onload = function() {
  console.log("Page Loaded");
  let button = document.getElementById("login-btn").addEventListener("click", login);

}

function login() {

  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;

  if (username && password) {

    let errorContainer = document.getElementById('error-message');
    errorContainer.setAttribute('hidden', true)

    let resData = fetch('http://localhost:8080/home/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({username, password})
    }).then(res =>{
      console.log(res);
      // if (res.status != 200) {
      //     errorContainer.removeAttribute('hidden')
      //     errorContainer.innerText = 'Login failed'
      //     return
      // }
      return res.json()
    })
    .then(data => {
      console.log(data);
      let successMsgContainer = document.createElement('p')
      successMsgContainer.setAttribute('class', 'alert alert-success')
      successMsgContainer.innerText = `Login successful welcome`
      document.getElementById('login-container').appendChild(successMsgContainer)
    })

  } else {
    let errorContainer = document.getElementById('error-message');
    errorContainer.removeAttribute('hidden')
    errorContainer.innerText = "Provide a Username and Password"
  }

}