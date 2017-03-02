#Flextur System
Systemet skal kunne anvendes af både brugere og MidtTrafik. 
Brugerne skal kunne vedligeholde
profiloplysninger, bestille kørsler med oplysninger om antal personer og evt. hjælpemidler, samt se deres
historik. 

MidtTrafik skal kunne se oversigt over bestilte kørsler, godkende kørsler, tildele biler, registrere
kørsler med beregnet pris, samt kommentere disse, og endelig se oversigter over afholdte ture, pr. bruger og pr. tidsinterval.
API til eksterne systemer.

Da priserne fastlægges eksternt, er der udarbejdet noget kode til beregning af disse, som udleveres som en
jar-fil. Koden kan returnere prisen, afhængig af kommune og ugedag. Beregningen er tidskrævende, og bør
derfor udføres i en selvstændig (parallel) proces.


Ønsker og krav til det nye system
Det nye system skal indarbejde følgende ønsker og krav:
Letforståelige, intuitive interfaces.

Hurtig feedback på handlinger i brugergrænsefladen. Brug af asynkrone og parallelle processer,
hvor det er muligt.

Oplysninger skal persisteres i en database. Vi ønsker oplysninger om brugere, ture, biler og priser.
Da brugeres personnumre indgår, skal de behandles med diskretion. De må således ikke anvendes
som nøgler noget sted.

Systemet laves her og nu som en enkeltbrugerløsning, men ønskes på sigt flyttet til en webplatform.
Den anvendte arkitektur bør tage hensyn til dette.

Systemet skal kunne eksportere en CSV-fil med en oversigt over de enkelte brugeres ture pr.
måned.
