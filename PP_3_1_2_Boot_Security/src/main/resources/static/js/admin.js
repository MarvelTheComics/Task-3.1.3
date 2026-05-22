openEditModal()
openDeleteModal()
loadUsers()
deleteUser()
editUser()

 async function loadUsers() {
     const usersInfo = document.getElementById('usersInfo')
     try {
         const resp = await fetch('api/users')
         if (!resp.ok) {
             throw new Error('Error loading')
         }
         const data = await resp.json()
         usersInfo.innerHTML = "";
         data.forEach(user => {
             usersInfo.innerHTML += `
            <tr>
                <td>${user.id}</td>
                <td>${user.email ?? ""}</td>
                <td>${user.name ?? ""}</td>
                <td>${user.secondName ?? ""}</td>
                <td>${user.age ?? ""}</td>
                <td>${user.eyeColor ?? ""}</td>
                <td>${user.roles.join(",") ?? ""}</td>
                <td><button type="button" 
                     class="btn btn-info edit-btn" 
                     data-bs-toggle="modal" 
                     data-bs-target="#editModal"
                     data-id="${user.id}"
                     data-email="${user.email ?? ""}"
                     data-name="${user.name ?? ""}"
                     data-secondname="${user.secondName ?? ""}"
                     data-eyecolor="${user.eyeColor ?? ""}"
                     data-age="${user.age ?? ""}"
                     data-role="${user.roles.join(", ") ?? ""}">
                     Edit
                     </button>
                </td>
                <td><button type="button"
                     class=" btn btn-danger delete-btn"
                     data-bs-toggle="modal"
                     data-bs-target="#deleteModal"
                     data-id="${user.id ?? ""}"
                     data-email="${user.email ?? ""}"
                     data-name="${user.name ?? ""}"
                     data-secondname="${user.secondName ?? ""}"
                     data-eyecolor="${user.eyeColor ?? ""}"
                     data-age="${user.age ?? ""}"
                     data-role="${user.roles.join(", ") ?? ""}">
                     Delete
                     </button>
                </td>
            </tr>`
         })
     } catch (e) {
         console.error(e)
         usersInfo.style.color = 'red'
         usersInfo.innerHTML = 'Error loading'
     }
 }

async function openEditModal() {
    document.addEventListener('click', (e) => {

        const btn = e.target.closest('.edit-btn');

        if (!btn) return;

        document.getElementById('idEdit').value =
            btn.dataset.id

        document.getElementById('emailEdit').value =
            btn.dataset.email

        document.getElementById('nameEdit').value =
            btn.dataset.name

        document.getElementById('secondNameEdit').value =
            btn.dataset.secondname

        document.getElementById('eyeColorEdit').value =
            btn.dataset.eyecolor

        document.getElementById('ageEdit').value =
            btn.dataset.age

        document.getElementById('roleEdit').value =
            btn.dataset.role
    });
}

async function openDeleteModal() {
    document.addEventListener('click', (e) => {

        const btn = e.target.closest('.delete-btn');

        if (!btn) return;

        document.getElementById('idDelete').value =
            btn.dataset.id;

        document.getElementById('emailDelete').value =
            btn.dataset.email;

        document.getElementById('nameDelete').value =
            btn.dataset.name;

        document.getElementById('secondNameDelete').value =
            btn.dataset.secondname;

        document.getElementById('eyeColorDelete').value =
            btn.dataset.eyecolor;

        document.getElementById('ageDelete').value =
            btn.dataset.age;

        document.getElementById('roleDelete').value =
            btn.dataset.role;
    });
}

async function deleteUser() {
    const formDelete = document.getElementById('deleteUser')
    formDelete.addEventListener('submit', async (e) => {
        e.preventDefault()

        const userId = document.getElementById('idDelete').value
        try {
            const resp = await fetch(`/api/users/${userId}`, {
                method: 'DELETE'
            })
            if (!resp.ok) {
                throw new Error("Error deleting")
            }
            alert('User deleted')
            await loadUsers();
            bootstrap.Modal.getInstance(
                document.getElementById('deleteModal')
            ).hide();
        } catch (e) {
            console.error(e)
        }
    })
}
async function editUser() {
    const formEdit = document.getElementById('editUser')
    formEdit.addEventListener('submit', async (e) => {
        e.preventDefault()

        const user = {
            id: Number(document.getElementById('idEdit').value),
            email: document.getElementById('emailEdit').value,
            name: document.getElementById('nameEdit').value,
            secondName: document.getElementById('secondNameEdit').value || null,
            eyeColor: document.getElementById('eyeColorEdit').value || null,
            age: Number(document.getElementById('ageEdit').value) || null,
            roleId: Number(document.getElementById('roleEdit').value)
        }
        const userId = document.getElementById('idEdit').value
        try {
            const resp = await fetch(`/api/users/${userId}`, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });
            if (!resp.ok) {
                throw new Error("Error editing");
            }
            alert('User updated');
            await loadUsers();
            bootstrap.Modal.getInstance(
                document.getElementById('editModal')
            ).hide();
        } catch (e) {
            console.error(e);
        }
    })
}



