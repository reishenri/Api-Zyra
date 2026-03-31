function formatTimeAgo(dateString) {
    if (!dateString) return "-";

    const now = new Date();
    const past = new Date(dateString);

    const diffMs = now - past;
    const diffSeconds = Math.floor(diffMs / 1000);
    const diffMinutes = Math.floor(diffSeconds / 60);
    const diffHours = Math.floor(diffMinutes / 60);

    if (diffSeconds < 60) {
        return "Agora mesmo";
    } else if (diffMinutes < 60) {
        return `${diffMinutes} min atrás`;
    } else if (diffHours < 24) {
        return `${diffHours} h atrás`;
    } else {
        return past.toLocaleDateString();
    }
}

async function loadLatestData() {
    try {
        const latestResponse = await fetch("http://localhost:8080/api/sensor/latest");
        const latestData = await latestResponse.json();

        if (latestData) {
            document.getElementById("temperature").textContent =
                (latestData.temperature != null ? latestData.temperature : "--") + " °C";

            document.getElementById("airHumidity").textContent =
                (latestData.airHumidity != null ? latestData.airHumidity : "--") + " %";

            document.getElementById("soilMoisture").textContent =
                (latestData.soilMoisture != null ? latestData.soilMoisture : "--") + " %";

            document.getElementById("alert").textContent =
                (latestData.alert != null ? latestData.alert : "--");

            document.getElementById("lastUpdate").textContent =
                formatTimeAgo(latestData.createdAt);
        } else {
            document.getElementById("temperature").textContent = "-- °C";
            document.getElementById("airHumidity").textContent = "-- %";
            document.getElementById("soilMoisture").textContent = "-- %";
            document.getElementById("alert").textContent = "--";
            document.getElementById("lastUpdate").textContent = "--";
        }

        const allResponse = await fetch("http://localhost:8080/api/sensor");
        const allData = await allResponse.json();

        const table = document.getElementById("historyTable");
        table.innerHTML = "";

        if (Array.isArray(allData)) {
            allData.forEach(item => {
                const row = `
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.temperature != null ? item.temperature : "--"} °C</td>
                        <td>${item.airHumidity != null ? item.airHumidity : "--"} %</td>
                        <td>${item.soilMoisture != null ? item.soilMoisture : "--"} %</td>
                        <td>${formatTimeAgo(item.createdAt)}</td>
                    </tr>
                `;
                table.innerHTML += row;
            });
        }

    } catch (error) {
        console.error("Erro ao buscar dados:", error);
        alert("Erro ao carregar dados da API.");
    }
}

loadLatestData();