
$('#updateUserModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var firstName = button.data('first-name')
    var lastName = button.data('last-name')
    var modal = $(this)
    modal.find('.modal-body #floatingFirstName').val(firstName)
    modal.find('.modal-body #floatingLastName').val(lastName)
})