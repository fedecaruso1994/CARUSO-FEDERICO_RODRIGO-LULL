window.addEventListener('load', function () {
    (function(){

      const url = '/odontologos/listar';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(dentist of data){
            var table = document.getElementById("dentistTable");
            var dentistRow =table.insertRow();
            let tr_id = 'tr_' + dentist.id;
            dentistRow.id = tr_id;

            let deleteButton = '<button' +
                          ' id=' + '\"' + 'btn_delete_' + dentist.id + '\"' +
                          ' type="button" onclick="deleteBy('+dentist.id+')" class="btn btn-danger btn_delete">' +
                          '&times' +
                          '</button>';

            let modifyButton = '<a href="odontologoActualizar.html?id=' + dentist.id + '" class="btn-form btn text-white border-0 btn_modify">Editar</a>';

            dentistRow.innerHTML =
                    '<td class=\"td_id\">' + dentist.id + '</td>' +
                    '<td class=\"td_nombre\">' + dentist.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + dentist.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_matricula\">' + dentist.matricula + '</td>' +
                    '<td>' + deleteButton + '</td>' +
                    '<td>' + modifyButton + '</td>';
        };
    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologoListar.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
 })