async function header() {
    let header
    try {
        const response = await fetch("/api/me")
        const data = await response.json()
        header = document.getElementById('myHeader')
        header.innerHTML = `
            <span class="fw-bold">${data.email}</span>
            <span> with Roles: </span>
            <span>${data.roles.join(",")}</span>
        `;
    } catch (e) {
        header.style.color = 'red'
        header.innerHTML = 'Error loading'
    }
}
header()