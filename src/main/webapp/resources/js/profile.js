$(document).ready(function(){
    $('.js-edit, .js-save, .js-cancel').on('click', function(){
        const $form = $(this).closest('form');
        $form.toggleClass('is-readonly is-editing');
        const isReadonly  = $form.hasClass('is-readonly');
        $form.find('input,textarea').prop('disabled', isReadonly);
        $form.find('.new-image').prop('hidden', isReadonly);

    });
});


const save = document.getElementById('js-save')
if (save!=null) {
    save.onclick = function () {
        document.getElementById('edit-portfolio').submit();
    }
}