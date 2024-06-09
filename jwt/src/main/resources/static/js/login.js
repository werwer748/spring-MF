const login = () => {
    const formData = {
        userId: document.getElementById("userId").value,
        password: document.getElementById("password").value,
    };

    fetch("/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Login Failed!!");
            }
            return response.json();
        })
        .then((data) => {
            const accessToken = data.accessToken;

            if (accessToken) {
                localStorage.setItem("accessToken", accessToken);
                const parseToken = jwtParsing(accessToken);

                parseToken.authorities.forEach((role) => {
                    switch (role) {
                        case "ROLE_USER":
                            document.getElementById("userMenu").style.display = "block";
                            break;
                        case "ROLE_MANAGER":
                            document.getElementById("managerMenu").style.display = "block";
                            break;
                        case "ROLE_ADMIN":
                            document.getElementById("adminMenu").style.display = "block";
                            break;
                        default:
                            break;
                    }
                });

                document.getElementById("loginForm").style.display = "none";
                document.getElementById("greeting").style.display = "block";
                document.getElementById("userIdDisplay").innerText = parseToken.userId;
            } else {
                console.log("Invalid JWT token received")
            }
        })
        .catch((e) => {
            console.log(e)
        });
};

const jwtParsing = (token) => {
    const tokenPayload = token.split(".")[1];
    const decodedPayload = atob(tokenPayload);
    console.log(decodedPayload);

    return JSON.parse(decodedPayload);
};

const logout = () => {
    localStorage.removeItem("accessToken")
    location.href = "/";
};