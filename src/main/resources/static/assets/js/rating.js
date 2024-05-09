const stars = document.querySelectorAll('.star');
stars.forEach(star => {
    star.addEventListener('click', function() {
        // Get the value of the clicked star
        const rating = this.getAttribute('data-value');
        $('#ratingValue').val(rating)
    });
});
var idMenuId = document.querySelector('#menuId').value
var rating = document.querySelector('#ratingMenuId').setAttribute("value", idMenuId)
console.log(idMenuId)
