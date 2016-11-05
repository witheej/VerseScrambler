<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Verse Scrambler Homepage</title>
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/additional-methods.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css"
	type="text/css">
</head>
<script type="text/javascript">
	(function($, window, document) {

		$('document')
				.ready(
						function() {

							var $bookSelection = $('#bookSelection');
							var $chapterSelection = $('#chapterSelection');
							var $verseSelection = $('#verseSelection');

							$bookSelection.val('noBookSelected')
							$chapterSelection.val("");
							$verseSelection.val("");
							$chapterSelection.prop('disabled', true);
							$verseSelection.prop('disabled', true);

							$bookSelection.on('change', onBookChange);

							$chapterSelection.on('change', onChapterChange);

							function onChapterChange() {
								if ($bookSelection.val() == "") {
									$verseSelection.val("");
									$verseSelection.prop('disabled', true);
								} else {
									var b = encodeURIComponent($bookSelection
											.val());
									var c = $chapterSelection.val();
									var url = '/VerseScrambler/versesInChapter?b='
											+ b + '&c=' + c;
									$
											.get(
													url,
													function(data, status) {
														$verseSelection
																.html("");
														var z;
														for (z = 1; z <= parseInt(
																data.limit, 10); z++) {
															$('<option>')
																	.val(z)
																	.text(z)
																	.appendTo(
																			'#verseSelection');
														}

													}, 'json');
									$verseSelection.val('1');
									$verseSelection.prop('disabled', false);
								}
							}

							function onBookChange() {
								if ($bookSelection.val() == 'noBookSelected') {
									$chapterSelection.val("");
									$chapterSelection.html("");
									$verseSelection.val("");
									$verseSelection.html("");
									$chapterSelection.prop('disabled', true);
									$verseSelection.prop('disabled', true);
								} else {
									$chapterSelection.html("");
									var C = numberOfChaptersInSelectedBook();
									var z;
									for (z = 1; z <= C; z++) {
										$('<option>').val(z).text(z).appendTo(
												'#chapterSelection');
									}
									$chapterSelection.val('1');
									$chapterSelection.prop('disabled', false);
									$chapterSelection.change();
								}
							}

							function numberOfChaptersInSelectedBook() {
								switch ($bookSelection.val()) {
								case 'Obadiah':
								case 'Philemon':
								case '2 John':
								case '3 John':
								case 'Jude':
									return 1;
								case 'Haggai':
									return 2;
								case 'Joel':
								case 'Nahum':
								case 'Habakkuk':
								case 'Zephaniah':
								case '2 Thessalonians':
								case 'Titus':
								case '2 Peter':
									return 3;
								case 'Ruth':
								case 'Jonah':
								case 'Malachi':
								case 'Philippians':
								case 'Colossians':
								case '2 Timothy':
									return 4;
								case 'Lamentations':
								case '1 Thessalonians':
								case 'James':
								case '1 Peter':
								case '1 John':
									return 5;
								case 'Galatians':
								case 'Ephesians':
								case '1 Timothy':
									return 6;
								case 'Micah':
									return 7;
								case 'Song of Songs':
									return 8;
								case 'Amos':
									return 9;
								case 'Ezra':
								case 'Esther':
									return 10;
								case 'Ecclesiastes':
								case 'Daniel':
									return 12;
								case 'Nehemiah':
								case '2 Corinthians':
								case 'Hebrews':
									return 13;
								case 'Hosea':
								case 'Zechariah':
									return 14;
								case 'Mark':
								case 'Romans':
								case '1 Corinthians':
									return 16;
								case 'Judges':
								case 'John':
									return 21;
								case '1 Kings':
								case 'Revelation':
									return 22;
								case 'Joshua':
								case '2 Samuel':
								case 'Luke':
									return 24;
								case '2 Kings':
									return 25;
								case 'Leviticus':
									return 27;
								case 'Matthew':
								case 'Acts':
									return 28;
								case '1 Chronicles':
									return 29;
								case '1 Samuel':
								case 'Proverbs':
									return 31;
								case 'Deuteronomy':
									return 34;
								case 'Numbers':
								case '2 Chronicles':
									return 36;
								case 'Exodus':
									return 40;
								case 'Job':
									return 42;
								case 'Ezekiel':
									return 48;
								case 'Genesis':
									return 50;
								case 'Jeremiah':
									return 52;
								case 'Isaiah':
									return 66;
								case 'Psalms':
									return 150;
								default:
									alert('Error in chapter validation switch statement.');
								}

							}

						});

	}(window.jQuery, window, document));
</script>
<body>
	<div class="container-fluid">
		<div class="jumbotron">
			<div class="container-fluid">
				<h1>Verse Scrambler!</h1>
				<h3>
					Welcome,
					<sec:authentication property="name" />
					!
				</h3>
				<p>Enter a verse and click "Submit" to begin.</p>
				<form:form id="referenceForm" commandName="submittedVerse"
					action="scrambler">
					<div class="row">
						<div class="col-xs-12">
							<sec:authorize ifAnyGranted="ROLE_TESTER">
								<form:radiobutton path="mode" value="veryeasy" />
							Very Easy
						</sec:authorize>
							<form:radiobutton path="mode" value="easy" checked="checked" />
							Easy
							<form:radiobutton path="mode" value="normal" />
							Normal
							<form:radiobutton path="mode" value="hard" />
							Hard
						</div>
					</div>
					<div class="row">
						<div class="col-xs-2 home-input">
							<label for="bookSelection">Book:<form:select
									id="bookSelection" name="book" path="book" class="form-control">
									<option value="noBookSelected">Select A Book</option>
									<option value="Genesis">Genesis</option>
									<option value="Exodus">Exodus</option>
									<option value="Leviticus">Leviticus</option>
									<option value="Numbers">Numbers</option>
									<option value="Deuteronomy">Deuteronomy</option>
									<option value="Joshua">Joshua</option>
									<option value="Judges">Judges</option>
									<option value="Ruth">Ruth</option>
									<option value="1 Samuel">1 Samuel</option>
									<option value="2 Samuel">2 Samuel</option>
									<option value="1 Kings">1 Kings</option>
									<option value="2 Kings">2 Kings</option>
									<option value="1 Chronicles">1 Chronicles</option>
									<option value="2 Chronicles">2 Chronicles</option>
									<option value="Ezra">Ezra</option>
									<option value="Nehemiah">Nehemiah</option>
									<option value="Esther">Esther</option>
									<option value="Job">Job</option>
									<option value="Psalms">Psalms</option>
									<option value="Proverbs">Proverbs</option>
									<option value="Ecclesiastes">Ecclesiastes</option>
									<option value="Song of Solomon">Song of Solomon</option>
									<option value="Isaiah">Isaiah</option>
									<option value="Jeremiah">Jeremiah</option>
									<option value="Lamentations">Lamentations</option>
									<option value="Ezekiel">Ezekiel</option>
									<option value="Daniel">Daniel</option>
									<option value="Hosea">Hosea</option>
									<option value="Joel">Joel</option>
									<option value="Amos">Amos</option>
									<option value="Obadiah">Obadiah</option>
									<option value="Jonah">Jonah</option>
									<option value="Micah">Micah</option>
									<option value="Nahum">Nahum</option>
									<option value="Habakkuk">Habakkuk</option>
									<option value="Zephaniah">Zephaniah</option>
									<option value="Haggai">Haggai</option>
									<option value="Zechariah">Zechariah</option>
									<option value="Malachi">Malachi</option>
									<option value="New Testament">New Testament</option>
									<option value="Matthew">Matthew</option>
									<option value="Mark">Mark</option>
									<option value="Luke">Luke</option>
									<option value="John">John</option>
									<option value="Acts">Acts</option>
									<option value="Romans">Romans</option>
									<option value="1 Corinthians">1 Corinthians</option>
									<option value="2 Corinthians">2 Corinthians</option>
									<option value="Galatians">Galatians</option>
									<option value="Ephesians">Ephesians</option>
									<option value="Philippians">Philippians</option>
									<option value="Colossians">Colossians</option>
									<option value="1 Thessalonians">1 Thessalonians</option>
									<option value="2 Thessalonians">2 Thessalonians</option>
									<option value="1 Timothy">1 Timothy</option>
									<option value="2 Timothy">2 Timothy</option>
									<option value="Titus">Titus</option>
									<option value="Philemon">Philemon</option>
									<option value="Hebrews">Hebrews</option>
									<option value="James">James</option>
									<option value="1 Peter">1 Peter</option>
									<option value="2 Peter">2 Peter</option>
									<option value="1 John">1 John</option>
									<option value="2 John">2 John</option>
									<option value="3 John">3 John</option>
									<option value="Jude">Jude</option>
									<option value="Revelation">Revelation</option>
								</form:select></label>
						</div>
						<div class="col-xs-2 home-input">
							<label for="chapterSelection">Chapter:<form:select
									id="chapterSelection" name="chapter" path="chapter" type="text"
									class="form-control" disabled="true">
									<option value="1">1</option>
								</form:select></label>
						</div>
						<div class="col-xs-2 home-input">
							<label for="verseSelection">Verse:<form:select
									id="verseSelection" name="verse" path="verse" type="text"
									class="form-control" disabled="true">
									<option value="1">1</option>
								</form:select></label>
						</div>
						<div class="col-xs-1">
							<label for="submit">Submit:<input id="submit"
								name="submit" type="submit" value="Go!"
								class="form-control btn-primary" /></label>
						</div>
					</div>
				</form:form>
			</div>
			<a class="btn btn-warning" href="j_spring_security_logout">Logout</a>
		</div>
	</div>
</body>
</html>