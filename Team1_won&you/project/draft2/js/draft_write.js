$(document).ready(function(){
    var selectedDocument = $("select[name='document'] option:selected").val();
    var applyingTitle = $("input[name='applyingTitle']").val();
    var applyingContents = $("textarea[name='applyingContents']").val();

    console.log(selectedDocument);
    console.log(applyingTitle);
    console.log(applyingContents);
})