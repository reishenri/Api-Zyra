const form = document.getElementById("registerForm");
const message = document.getElementById("message");

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const userData = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        address: document.getElementById("address").value,
        city: document.getElementById("city").value,
        state: document.getElementById("state").value,
        zipCode: document.getElementById("zipCode").value,
        plan: document.getElementById("plan").value
    };

    try {
        const response = await fetch("http://localhost:8080/api/users/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Erro ao cadastrar usuário");
        }

        const data = await response.json();

        message.style.color = "#22c55e";
        message.textContent = `Usuário cadastrado com sucesso! ID: ${data.id}`;

        form.reset();
    } catch (error) {
        console.error("Erro no cadastro:", error);
        message.style.color = "#ef4444";
        message.textContent = "Não foi possível cadastrar o usuário.";
    }
});