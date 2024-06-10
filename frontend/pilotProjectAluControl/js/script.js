/**
 * National College of Ireland - NCI
 *    Higher Diploma in Computing
 *         Final Project
 *              ---
 * Author: Sergio Vinicio da Silva Oliveira
 * ID: x23170981@student.ncirl.ie
 * Project Commencing May 2024
 * Version: 1.0
 */
//Reference: https://getbootstrap.com/docs/5.0/forms/validation/
//Checking form submissions if there are invalid fields: Used by "Rent Form"/"Rent Form Edit"
(function () {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                    console.log('Checking the form...')
                }
                form.classList.add('was-validated')
            }, false)
        })
})()



/**
 Method: Generic function to perform an AJAX request and handle success and error responses
 Type: GET
 */
function ajaxRequest(url, successCallback)
{
    $.ajax({
        url: url,     //indicates the endpoint from the argument to the ajaxRequest function
        type: "GET",  //HTTP request methods used to RETRIEVE data from the server (backend)

        //If the request is successful, a callback function will be called from the argument to the ajaxRequest function
        success: successCallback,

        //If there is any error
        error: function(xhr, status, error)
        {
            console.error(error);
            let errorMessage = xhr.responseText;
            alert("From the Server: " + errorMessage);
        }
    });
}


/** Function: Handle errors, showing the error message to the user */
function handleError(errorMessage)
{
    alert(errorMessage);
}