const listAllPosts = document.getElementsByClassName("reply-button");
if (listAllPosts != null){
    for (let i = 0; i <listAllPosts.length; i++) {
        listAllPosts[i].onclick = function (){
            let id = listAllPosts[i].id
            id = id.split('-')[2];

          const hide = document.getElementById("reply-"+id).hidden;
          document.getElementById("reply-" + id).hidden = !hide;
        }
    }
}