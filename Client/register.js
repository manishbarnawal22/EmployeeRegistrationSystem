function fetchEmployees(e)
{
console.log(e);
  const fetchEmpUrl = 'http://localhost:8080/employee/getAllEmployees';  
  fetch(fetchEmpUrl).then(data=>{
	
	return data.json();
    
    }).then(data=>{
	
		createEmployeesTable(data);
		console.log(data);
	})
  .catch(function(error) {
	  console.log(error);
    alert(error);// If there is any error you will catch them here
  });
}

function register(e)
{
  console.log(e);
  e.preventDefault();
  const createEmpUrl = 'http://localhost:8080/employee/create';
  // The parameters we are gonna pass to the fetch function
  
  let formEle = document.forms.registerForm;
  let inputData={
	firstname:formEle.firstname.value,
	lastname:formEle.lastname.value,
	dob:formEle.dob.value,
	gender:formEle.gender.value,
	department:formEle.department.value
  }
  
  let fetchPostData = { 
    method: 'POST', 
    body: JSON.stringify(inputData),
    headers: {
		'Accept':'application/json',
		'Content-Type':'application/json'
	}
}
  
  fetch(createEmpUrl, fetchPostData)
  .then(function(data) 
		{
			if(data.status === 201)
			{
				document.getElementById('registrationSuccessMessage').style.display = "block";
				setTimeout(()=>{
				document.getElementById('registrationSuccessMessage').style.display = "none";
				document.getElementById('signup').style.display='none';
				document.querySelector('.overlay').style.display='none';	
				document.querySelector('body').style.overflow='';
			},500);
		}
    
    })
  .catch(function(error) {
	console.log(error);
    alert(error);
  });
}

function closeModal(){
	document.getElementById('signup').style.display='none';	
	document.querySelector('.overlay').style.display='none';	
	document.querySelector('body').style.overflow='';
}


function createEmployeesTable(data)
{
	var tableDataHtml = "<div id='employeeDataTableId'>"
						tableDataHtml+=		"<table class='table table-striped'>"
						tableDataHtml+=		  "<thead>"
						tableDataHtml+=			"<tr>"
						tableDataHtml+=			  "<th scope='col'>First Name</th>"
						tableDataHtml+=			  "<th scope='col'>Last Name</th>"
						tableDataHtml+=			  "<th scope='col'>Gender</th>"
						tableDataHtml+=			  "<th scope='col'>Date Of Birth</th>"
						tableDataHtml+=			  "<th scope='col'>Department</th>"
						tableDataHtml+=			"</tr>"
						tableDataHtml+=		  "</thead>"
						tableDataHtml+=		  "<tbody>";
				for(var i in data)
				{
					var firstName = data[i].firstname;
					var lastName = data[i].lastname;
					var gender = data[i].gender;
					var dob = data[i].dob;
					var department = data[i].department;
					
						  tableDataHtml+= "<tr>"
						  tableDataHtml+= "<td scope='row'>"+firstName+"</td>"
						  tableDataHtml+= "<td>"+lastName+"</td>"
						  tableDataHtml+= "<td>"+gender+"</td>"
						  tableDataHtml+= "<td>"+new Date(dob).toDateString()+"</td>"
						  tableDataHtml+= "<td>"+department+"</td>"
						  tableDataHtml+= "</tr>"
				}
				tableDataHtml+="</tbody>"
				tableDataHtml+="</table>"
				tableDataHtml+="</div>";
					
				document.getElementById('employeesDataContainer').innerHTML=tableDataHtml;
		}

		
function signup(){
	
	document.getElementById('firstname').value="";
	document.getElementById('lastname').value="";
	document.getElementById('dob').value="";
	document.getElementById('gender').value="";
	document.getElementById('department').value="";

	document.getElementById('signup').style.display='flex';
	document.querySelector('.overlay').style.display='block';
	document.querySelector('body').style.overflow='hidden';
}