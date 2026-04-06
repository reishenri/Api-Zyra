const form = document.getElementById("loginForm");
const message = document.getElementById("message");

form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const loginData = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        });

        const data = await response.json();

        if (!data || !data.id) {
            throw new Error("Login inválido");
        }

        // 🔥 salva usuário logado
        localStorage.setItem("user", JSON.stringify(data));

        message.style.color = "#22c55e";
        message.textContent = "Login realizado com sucesso!";

        // 🔥 redireciona
        setTimeout(() => {
            window.location.href = "index.html";
        }, 1000);

    } catch (error) {
        console.error(error);
        message.style.color = "#ef4444";
        message.textContent = "Email ou senha inválidos";
    }
});