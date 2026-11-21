# The Russian of the translations

How the Russian side of the store listing and the website should sound. The
English listing (`fastlane/metadata/android/en-US/`), `website/i18n/en.toml`,
the English captions and the donate page are the original; the Russian is
their translation, sense for sense, and says what the English says (the
owner, 2026-11-06: what one language names, the other names).

**Where this comes from.** Ported on 2026-11-21 from the Riding the Boar
site, whose owner is the same: the rules 1–16 below are his corrections to
the Russian of those essays, entered there with his wording and the date,
and they hold for any Russian he reads. The essays' examples are kept as
they stood, because they show the fault better than a paraphrase would.
Since 2026-11-21 no AI detector gates the Russian here either (the owner's
choice for this rewrite, following his decision of 2026-11-20 on Riding the
Boar): «ё» and the spaced em dash are written, and the stiff «и … и … и»
chains and «X идут за Y» shapes that were written to pass Sapling on
2026-11-05 are gone.

**The method.**

1. Read the English unit in full; find the app's own Russian for every term
   (`app/src/main/res/values-ru/strings.xml`, the catalog
   `core/src/main/resources/texts/texts_ru.properties`): the site names
   things as the app does — «мева», «смещённый знак», «Тушь», «О приложении
   и источниках».
2. A person's name takes the form of a Russian reference page opened in the
   run (Жан Меёс: ru.wikipedia «Меёс, Жан» and his Russian edition, «Мир»,
   1988); with no such page it is transcribed as the app does (Сванте Янсон,
   Эдвард Хеннинг).
3. Write the Russian whole, with no tool running; read it once aloud
   without the English and once against it, unit by unit.
4. `python3 tools/ru_check.py` — every fault fixed, every note read and
   decided.
5. Read it once more as a visitor of the site: where attention drops or a
   sentence sounds translated, rewrite it by hand and check again.

**How the file grows.** Every correction the owner makes to the Russian is
entered here the same day: what stood, what he put, and the rule it
teaches. Where a script can see the fault, the token goes into
`tools/ru_rules/Translationese.yml`, and `tools/ru_check.py` names it.

## The rules

## The site

### S1. The app's words, not new ones

> закреплённые дела стоят на странице первыми; «Тушь», «Бумага»; смещённый знак (ལོག་མེན, log men)
>
> — the rewrite of 2026-11-21

A visitor who installs the app meets its menu, its rows and its sheets.
A term the site words differently from the app is a second term for one
thing.

**Rule.** Every term of the Russian site is the app's own Russian term;
where the app has none, the English original is translated and the app's
next string follows the site.

## The essays' rules (Riding the Boar, 2026-11-19 and 2026-11-20)

### 1. A made thing is «рукотворная», not «сделанная»

> сделанная вещь в облике человека, сделанная ради… → рукотворная вещь, сделанная ради…
> сделанная вещь не могла ответить → рукотворная вещь не могла ответить
>
> — the owner, 2026-11-19, essay 8

English «a made thing» is a participle doing the work of an adjective. In
Russian the participle «сделанная» before a noun reads as a calque; the
language has an adjective for the idea, «рукотворный». The same
participle twice in one sentence («сделанная… сделанная») is a sign the
English was carried word for word.

**Rule.** Where English uses a bare participle as a quality («a made
thing», «a written word», «a built servant»), look for the Russian
adjective of that quality before keeping the participle; never repeat
one participle in a sentence.

### 2. What the noun already carries is not said again

> рукотворная вещь в облике человека → рукотворная вещь
>
> — the owner, 2026-11-19, essay 8

The essay says «a made thing in the shape of a man». The owner cut «в
облике человека»: in Russian the golem is already the subject of the
sentence and the tale, and the descriptor slows the sentence for nothing
the reader lacks.

**Rule.** A descriptive phrase the English needs for its rhythm may go
when the Russian sentence already makes it plain. The check does not
count such a cut as a loss; the owner's review is where it is judged.

### 3. A negative in English is said as a positive in Russian

> самое мрачное в ней то, что ничего в ней не ломается → …что в ней всё работает как задумано
>
> — the owner, 2026-11-19, essay 8

English «nothing in it malfunctions» states a fact by denying its
opposite. The Russian «ничего не ломается» keeps the denial and loses the
point, which is that the mechanism did exactly what it was built for.

**Rule.** When the English denies the opposite to make a positive point
(«nothing malfunctions», «it never failed to», «not without reason»), say
the positive in Russian.

### 4. A set phrase of Russian, not the English words

> отвечают бегло и в любой час → и в любое время дня и ночи
>
> — the owner, 2026-11-19, essay 8

«At any hour» carried literally is «в любой час», which Russian does not
say. Russian has the phrase for it.

**Rule.** An English idiom of time, place or measure («at any hour», «at
the end of the day», «a head taller») is rendered by the Russian idiom
for the same idea, not by its words.

### 5. A figure that does not bend in Russian is dropped

> домашнюю работу целой компании → работу целой компании
> домашней работы на свете куда больше → рутинной работы на свете куда больше
>
> — the owner, 2026-11-19, essay 8

The essay carries the golem's «housework» from the anchor into the
mirror: «the housework of an entire company», «more housework in the
world than hands». In English the word stretches to mean drudgery of any
kind. Russian «домашняя работа» does not stretch: it stays in the house,
and on a company it reads as a mistake.

**Rule.** A word the essay extends from the anchor into the mirror keeps
its literal Russian only in the anchor, where it is literal («работа по
дому» of the golem of Chełm). In the mirror it takes the Russian word for
what it has come to mean («работа», «рутинная работа»), or is dropped
when the noun alone carries it. Before translating, find each such word
of the essay: `tools/translate.py material` prints the terms in italics,
and the motifs are the words the three sections share.

### 6. A verb takes the government its sense needs

> здравая инженерия того рода, который мы, похоже, забыли → …о котором мы, похоже, забыли
>
> — the owner, 2026-11-19, essay 8

«Забыть что» is to forget a thing one had in hand; «забыть о чём» is to
forget that it exists at all. The essay means the second: a kind of
engineering that has dropped out of mind.

**Rule.** Where a Russian verb has two governments with different senses
(«забыть что / о чём», «думать что / о чём», «помнить что / о чём»),
choose by what the English means, not by the shape of its object.

### 7. An idiom is checked for what it means in Russian

> Держи эту букву на расстоянии вытянутой руки → Держи эту букву под рукой
>
> — a rewrite of 2026-11-19, essay 8; the owner: «these corrections you made are good»

«Within reach» means close by, ready to use. «Держать на расстоянии
вытянутой руки» reads in Russian as keeping something away, at a distance:
the opposite. «Под рукой» is the Russian for the English.

**Rule.** An idiom found for an English phrase is read once for what it
says in Russian on its own, before it stays.

### 8. The engineer's passage in the engineer's Russian

> одной строкой в файле конфигурации → просто строчкой в конфиге
> измени в нём одно слово, и слуга остановится → поменял одно слово — слуга встал
> Потом слуга растёт, потому что именно роста мы от него и хотим → Потом слуга растёт. Мы ведь этого и добивались.
>
> — rewrites of 2026-11-19, essay 8; the owner: «these corrections you made are good»

The mirror of an essay is about the reader's own work, and in Russian
engineers speak of it in their own register: «конфиг», «строчка», «слуга
встал». Written in book Russian, the same passage reads like a press
release, and it was the passage a detector marked.

**Rule.** In the mirror, the words and the rhythm engineers use among
themselves: their nouns, short clauses, a dash for a consequence, a
reason as its own short sentence. The anchor keeps its storyteller's
Russian.

### 9. The Russian turn of phrase over the English shape

> выключи его нарочно, пока ничего не горит, и посмотри, что упадёт и на кого → …что свалится и на чью голову
> дай своему слуге его вечер пятницы → устрой слуге его пятничный вечер
>
> — rewrites of 2026-11-19, essay 8; the owner: «these corrections you made are good»

**Rule.** Where Russian has a turn of its own for the image («на чью
голову», «устроить кому-то вечер»), it takes the place of the English
shape.

### 10. Not overzealous

> — the owner, 2026-11-19: «these corrections you made are good. just don't be overzelous»

A translation is reworked for its Russian, not past that: no sentence is
changed that no rule here asks to change. (Until 2026-11-20 a detector
reading at or above the line was a second reason to rework; the owner
dropped the detectors that day.)

### 11. A quotation in a Russian edition, not in our Russian

> — the owner, 2026-11-20: «For russian translations use some russian editions»

The four translations of 2026-11-20 put Goethe's ballad, the Grimms' tale
and Le Guin into Russian from the essays' English, although each has
Russian editions that a Russian reader knows. A quotation a reader has met
in a book reads as the book; our own version of it reads as a gloss.

**Rule.** Every quoted text that has a Russian edition is quoted from it,
opened in the run, and the names of the whole translation follow that
edition (on Riding the Boar, skill `article_translate`, step 2). Our own Russian is for texts
with no edition, and the report says which those are.

### 12. Code is written, not drawn

> Перерисуешь только код, и вернётся старый портрет → Перепишешь только код — и старый портрет вернётся
>
> — the owner, 2026-11-20, essay 12: «Перерисуешь только код — вообще не ясно, о чем сказано. Если программный код, то его пишут.»

The essay's «redraw» carries its image of the portrait over to the code;
in Russian «перерисовать код» says nothing a reader can picture.

**Rule.** The image stays with its own noun; the code gets the engineer's
verb: код пишут, переписывают, правят. A figure carried from one noun to
the next is checked on the second noun as well (rule 5).

### 13. «Огонь» stays put; a carried flame is the lamp

> ветер внутри задувал его огонь → ветер внутри гасил его светильник
>
> — the owner, 2026-11-20, essay 12: «в русском огонь обычно говорят о том огне, который не двигается. В таком контексте надо сказать о факеле, лампе или светильнике»

> научившего Балинуса пронести свет сквозь ветер → подсказавшего Балинусу, как пронести светильник сквозь ветер
>
> — the owner's correction, 2026-11-20, essay 12, the closing paragraph

**Rule.** «Огонь» is the fire that stays where it is — a hearth, a fire
in the field. The flame a person carries, shields or loses to the wind is
named by what holds it: светильник, лампа, факел, свеча — and named so
each time it returns, so the reader sees the same thing at the end as at
the start.

### 14. The adjective in its place, not tacked on

> Конвей оставил критерий, и вполне практический → Конвей поставил вполне практический критерий
>
> — the owner, 2026-11-20, essay 12: «запрети этот оборот, говори: поставил вполне практический критерий»

English «a criterion, and a rather practical one» adds the quality as an
afterthought; Russian «X, и вполне Y» copies the shape and reads as
translated. The verb changes too: a criterion is «поставлен» or
«предложен», not «оставлен».

**Rule.** Forbidden: «…, и вполне / весьма / довольно / очень
<adjective>» after a noun. The adjective goes before its noun
(`tools/ru_rules/Afterthought.yml`, at error level: `ru_check.py` refuses it).

### 15. The image of the original, said the Russian way

> и каждый квадратик всё так же дуется у своей старой границы → и каждый блок по-прежнему держится за свои старые границы
>
> — the owner, 2026-11-20, essay 12: «что это вообще значит?»
>
> Сменить курс позже — значит отказаться от своего детища, а это, по его словам, «больно и дорого». Поэтому платим мы редко. → Если курс меняют позже, от уже созданного приходится отказываться, а это, по его словам, «больно и дорого». Поэтому такую цену мы платим редко.
>
> — the owner, 2026-11-20, essay 12: «я понимаю, что ты хотел сказать, но это звучит странно»
>
> организация должна гнуться вслед за проектом → организация должна подстраиваться под проект
>
> — the owner, 2026-11-20, essay 12: «по-русски так не говорят»
>
> Так что перерисуй, кто с кем разговаривает, прежде чем перерисовывать квадратики системы → Так что перестрой потоки коммуникации прежде, чем переставлять блоки в схеме системы
>
> — the owner, 2026-11-20, essay 12: «это тоже грубая калька»

Boxes that «sulk at their old borders», an organization that «bends when
the design does», «so we seldom pay» with the price left in the sentence
before, «redraw who talks to whom»: each keeps the English shape and
loses the sense on the way. The Russian reader gets a picture he cannot
form, or a verb with nothing to act on.

**Rule.** A figure is kept only when a Russian reader sees the same
picture (rule 5); otherwise the sense is said in the words a Russian
engineer or manager uses: блоки в схеме, потоки коммуникации,
подстраиваться под. A verb whose object the English leaves in the
sentence before gets it back in Russian («такую цену мы платим»).

### 16. The closing turn spelled out, and set apart

> У нашего — тоже → У нашего проводника — наше
>
> пока то, что вверху, остаётся как есть, а вверху — мы → пока то, что вверху, неизменно. А вверху — мы сами
>
> — the owner's corrections, 2026-11-20, essay 12, the last lines

English «So does ours» leaves the noun and the verb for the reader to
recover; «У нашего — тоже» copies the ellipsis, and the reader has to go
back to find what «нашего» is. The landing, tacked on with «а», loses
its weight.

**Rule.** In the landing an ellipsis is spelled out: the noun is named
again and the parallel completed («его собственное лицо» → «наше»). The
last turn stands as a sentence of its own and takes the stress it needs
(«мы сами»).

## Single choices

| English | Russian | Essay, date |
| --- | --- | --- |
| a made thing | рукотворная вещь | 8, 2026-11-19 |
| at any hour | в любое время дня и ночи | 8, 2026-11-19 |
| the housework of an entire company | работа целой компании | 8, 2026-11-19 |
| more housework in the world | рутинной работы на свете больше | 8, 2026-11-19 |
| nothing in it malfunctions | в ней всё работает как задумано | 8, 2026-11-19 |
| of a kind we seem to have forgotten | того рода, о котором мы, похоже, забыли | 8, 2026-11-19 |
| keep that letter within reach | держи эту букву под рукой | 8, 2026-11-19 |
| a line in a configuration file | строчка в конфиге | 8, 2026-11-19 |
| and watch what falls, and on whom | и посмотри, что свалится и на чью голову | 8, 2026-11-19 |
| redraw the code alone | перепишешь только код | 12, 2026-11-20 |
| would not let his flame stay lit | гасил его светильник | 12, 2026-11-20 |
| boxes … sulking at their old borders | каждый блок по-прежнему держится за свои старые границы | 12, 2026-11-20 |
| So we seldom pay | Поэтому такую цену мы платим редко | 12, 2026-11-20 |
| What Conway left beside his law was a criterion, and a rather practical one | Рядом со своим законом Конвей поставил вполне практический критерий | 12, 2026-11-20 |
| the organization has to bend when the design does | организация должна подстраиваться под проект | 12, 2026-11-20 |
| redraw who talks to whom before you redraw the boxes of the system | перестрой потоки коммуникации прежде, чем переставлять блоки в схеме системы | 12, 2026-11-20 |
| the guide who taught Balīnūs to carry his light through the wind | проводник, подсказавший Балинусу, как пронести светильник сквозь ветер | 12, 2026-11-20 |
| So does ours | У нашего проводника — наше | 12, 2026-11-20 |
| while the above stays as it is, and the above is us | пока то, что вверху, неизменно. А вверху — мы сами | 12, 2026-11-20 |
