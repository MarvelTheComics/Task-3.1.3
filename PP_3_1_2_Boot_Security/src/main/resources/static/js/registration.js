async function createUser() {
    const form = document.getElementById('createUser')
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const user = {
            email: document.getElementById('emailCreate').value,
            name: document.getElementById('nameCreate').value,
            secondName: document.getElementById('secondNameCreate').value || null,
            eyeColor: document.getElementById('eyeColorCreate').value || null,
            age: Number(document.getElementById('ageCreate').value) || null,
            password: document.getElementById('passwordCreate').value,
            roleId: Number(document.getElementById('roleCreate').value) || null
        }

        try {
            const resp = await fetch('/api/registration',{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })

            if (!resp.ok) {
                throw new Error("Error loading")
            }
            alert('Пользователь создан!')

        } catch (e) {
            console.error(e)
        }
    });
}

createUser()