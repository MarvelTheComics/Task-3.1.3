
async function loadMe() {
    let myInfo = ""
    try {
        const resp = await fetch('api/me')
        if(!resp.ok) {
            throw new Error('Error loading')
        }
        const data = await resp.json()
        myInfo = document.getElementById('myInfo')
        myInfo.innerHTML = `
            <tr>
                <td>${data.id}</td>
                <td>${data.email || ""}</td>
                <td>${data.name || ""}</td>
                <td>${data.secondName || ""}</td>
                <td>${data.age || ""}</td>
                <td>${data.eyeColor || ""}</td>
                <td>${data.roles.join(",") || ""}</td>
            </tr>
            `;
    } catch (e) {
        myInfo.style.color = 'red'
        myInfo.innerHTML = 'Error loading'
    }
}
loadMe();

