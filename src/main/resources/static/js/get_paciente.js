window.addEventListener('load', function () {
    (function(){

      const url = '/pacientes/listar';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(patient of data){
            var table = document.getElementById("patientTable");
            var patientRow =table.insertRow();
            let tr_id = 'tr_' + patient.id;
            patientRow.id = tr_id;

            let deleteButton = '<button' +
                          ' id=' + '\"' + 'btn_delete_' + patient.id + '\"' +
                          ' type="button" onclick="deleteBy('+patient.id+')" class="btn btn-danger btn_delete">' +
                          '&times' +
                          '</button>';

            let modifyButton = '<a href="pacienteActualizar.html?id=' + patient.id + '" class="btn-form btn text-white border-0 btn_modify">Editar</a>';

            patientRow.innerHTML =
                    '<td class=\"td_id\">' + patient.id + '</td>' +
                    '<td class=\"td_nombre\">' + patient.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + patient.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + patient.dni + '</td>' +
                    '<td class=\"td_fecha_ingreso\">' + patient.fecha_ingreso + '</td>' +
                    '<td>' + deleteButton + '</td>' +
                    '<td>' + modifyButton + '</td>';
        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/pacienteListar.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })