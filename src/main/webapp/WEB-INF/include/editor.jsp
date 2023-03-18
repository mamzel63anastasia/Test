<script src="/plugin/ckeditor5-build-classic/ckeditor.js"></script>
<script>
  ClassicEditor
          .create( document.querySelector( '#editor' ), {
            toolbar: [
              'heading', '|',
              'bold', 'italic', 'link', '|',
              'alignment', 'insertTable', '|',
              'undo', 'redo',
            ]
          } )
          .then( editor => {
            window.editor = editor;
          } )
          .catch( err => {
            console.error( err.stack );
          } );
</script>