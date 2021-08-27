function loadNotes () {

    this.source = null;

    this.start = function () {

        var noteTable = document.getElementById("notes");

        this.source = new EventSource("/flux/note/feed");

        this.source.addEventListener("message", function (event) {

            // These events are JSON, so parsing and DOM fiddling are needed
            var note = JSON.parse(event.data);

            var row = commentTable.getElementsByTagName("tbody")[0].insertRow(0);
            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);
            var cell2 = row.insertCell(2);

            cell0.className = "author-style";
            cell0.innerHTML = note.author;

            cell1.className = "text";
            cell1.innerHTML = note.text;

            cell2.className = "book-id";
            cell2.innerHTML = note.bookId;

        });

        this.source.onerror = function () {
            this.close();
        };

    };

    this.stop = function() {
        this.source.close();
    }

}

note = new loadNotes();

/*
 * Register callbacks for starting and stopping the SSE controller.
 */
window.onload = function() {
    note.start();
};
window.onbeforeunload = function() {
    note.stop();
}