<!DOCTYPE html>
<html>
<head>
    <title>A2 Part 1: Parse the Phrase</title>
    <link rel="stylesheet" type="text/css" href="Phrase.css">
</head>
<body>
    <h1>Parse the Phrase</h1>
    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $phrase = $_POST["phrase"];
        $words = explode(" ", $phrase);
        $validTypes = array("WT1", "WT2", "WT3", "WT4");

        $validWords = array();
        foreach ($words as $word) {
            $type = getTypeOfWord($word);
            if ($type) {
                $validWords[] = array("word" => $word, "length" => strlen($word), "type" => $type);
            }
        }

        if (count($validWords) >= 3 && hasRealNumber($validWords)) {
            echo "<p>Your phrase parsed into words:</p>";
            echo "<table>";
            echo "<tr><th>Word</th><th>Length</th><th>Type</th></tr>";
            foreach ($validWords as $validWord) {
                echo "<tr><td>{$validWord['word']}</td><td>{$validWord['length']}</td><td>{$validWord['type']}</td></tr>";
            }
            echo "</table>";
        } else {
            echo "<p class='error'>Invalid phrase. Please make sure it has at least 3 words and contains at least one real number.</p>";
            echo "<p><a href='PhraseInput.html'>Go back</a></p>";
        }
    } else {
        echo "<p class='error'>Invalid request.</p>";
    }

    function getTypeOfWord($word) {
        if (ctype_alpha($word)) {
            return "alphabetic characters";
        } elseif (is_numeric($word) && strpos($word, '.') !== false) {
            return "real number";
        } elseif (ctype_digit($word)) {
            return "positive integer";
        }
        return "undefined type";
    }

    function hasRealNumber($words) {
        foreach ($words as $wordData) {
            if ($wordData['type'] == "real number") {
                return true;
            }
        }
        return false;
    }
    ?>
</body>
</html>
