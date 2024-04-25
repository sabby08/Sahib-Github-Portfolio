<!DOCTYPE html>
<html>
<head>
    <title>A2 Part 2: Filter the Phrase</title>
    <link rel="stylesheet" type="text/css" href="Phrase.css">
</head>
<body>
    <h1>Filter the Phrase</h1>
    <?php
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $phrase = $_POST["phrase"];
        $words = explode(" ", $phrase);

        $filteredWords = array();
        $filterType = '';

        if (isset($_POST['ShowWT1'])) {
            $filterType = 'WT1';
        } elseif (isset($_POST['ShowWT2'])) {
            $filterType = 'WT2';
        } elseif (isset($_POST['ShowWT3'])) {
            $filterType = 'WT3';
        }

        foreach ($words as $word) {
            $type = getTypeOfWord($word);
            if ($type === $filterType) {
                $filteredWords[] = array("word" => $word, "length" => strlen($word), "type" => $type);
            }
        }

        if (!empty($filteredWords)) {
            echo "<p>Filtered table:</p>";
            echo "<table>";
            echo "<tr><th>Word</th><th>Length</th><th>Type</th></tr>";
            foreach ($filteredWords as $filteredWord) {
                echo "<tr><td>{$filteredWord['word']}</td><td>{$filteredWord['length']}</td><td>{$filteredWord['type']}</td></tr>";
            }
            echo "</table>";
        } else {
            echo "<p class='error'>No words found for the selected type.</p>";
        }

    } else {
        echo "<p class='error'>Invalid request.</p>";
    }

    function getTypeOfWord($word) {
        if (ctype_alpha($word)) {
            return "WT1";
        } elseif (is_numeric($word) && strpos($word, '.') !== false) {
            return "WT2";
        } elseif (ctype_digit($word)) {
            return "WT3";
        }
        return "WT4";
    }
    ?>
    <p><a href="PhraseInput.html">Go back to Phrase Input</a></p>
</body>
</html>
