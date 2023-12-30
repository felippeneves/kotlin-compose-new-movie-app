package com.felippeneves.newmovieapp.core.domain.model

class MovieDetailsFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.GuardiansOfTheGalaxy -> {
            MovieDetails(
                id = 1,
                title = "Guardians of the Galaxy Vol. 3",
                genres = listOf("Action", "Adventure", "Comedy"),
                overview = "At their new headquarters on Knowhere,[a] the Guardians of the Galaxy are attacked by Adam Warlock, a Sovereign warrior created by High Priestess Ayesha, who seeks to destroy them for stealing from her.[b] After critically wounding Rocket, Adam is stabbed by Nebula, and flees. The Guardians' med-packs are useless at healing Rocket's wounds, who has a kill switch embedded in him by Orgocorp, a company helmed by the High Evolutionary, Rocket's creator. The Guardians travel to Orgocorp's headquarters to find the switch's override code and save Rocket's life.\n" +
                        "\n" +
                        "As Rocket lies unconscious, he recalls his past. He was found as a baby raccoon and was experimented on by the High Evolutionary, who sought to enhance and anthropomorphize animal lifeforms to create an ideal society called Counter-Earth. Rocket befriended his fellow Batch 89 test subjects: the otter Lylla, the walrus Teefs, and the rabbit Floor. The High Evolutionary was impressed by Rocket's growing intelligence and used his insight to fix a defect in later Humanimal batches, but planned to harvest Rocket's brain for further research and exterminate the obsolete Batch 89. Rocket freed his friends, but the High Evolutionary killed Lylla. Enraged, Rocket mauled the High Evolutionary, whose henchmen killed Teefs and Floor in the ensuing firefight. Rocket fled in a spaceship.\n" +
                        "\n" +
                        "In the present, the Ravagers, including an alternate version of Gamora,[c] help the Guardians infiltrate Orgocorp. They retrieve Rocket's file but discover that the code was removed, with the likely culprit being Theel, one of the High Evolutionary's advisors. The Guardians, along with Gamora, depart for Counter-Earth to find him. They are followed by Ayesha and Adam after the High Evolutionary, their race's creator, threatened to wipe out the Sovereign if they fail to retrieve Rocket. The Guardians reach Counter-Earth and are guided to the Arête Laboratories complex. Drax and Mantis remain with Gamora and Rocket, while Peter Quill, Groot, and Nebula travel to Arête. Nebula is forced to wait outside by guards as she is armed; Quill and Groot enter Arête, while Drax tricks Mantis into pursuing Quill's group. Gamora saves Rocket from being captured by Adam and the High Evolutionary's guard War Pig.\n" +
                        "\n" +
                        "Questioned by Quill, the High Evolutionary admits this version Counter-Earth's society is imperfect, so he bombards the planet, killing the Humanimals as well as Ayesha. Arête departs as a spaceship, with Nebula, Drax and Mantis boarding to rescue Quill and Groot, who instead escape Arête with Theel, who they kill before retrieving the code from his corpse and being rescued by Gamora in their ship. As Quill's group uses the code, Rocket flatlines and has a near-death experience, in which he reunites with Lylla, Teefs, and Floor. He learns from Lylla that his time has not yet come, as Quill uses the code to disable the kill switch and restarts Rocket's heart.\n" +
                        "\n" +
                        "Drax, Nebula, and Mantis encounter several genetically modified humanoid children on Arête before being captured. The other Guardians stage a rescue, leading to a battle against the High Evolutionary's forces. Kraglin fires on Arête with Knowhere, dooming Arête, then helps to save Knowhere's citizens from a counter-attack by the High Evolutionary's Hellspawn. Intent on retreat, the High Evolutionary's crew mutiny only to be killed by their leader. Drax, Nebula, and Mantis befriend three monstrous Abilisks to escape and reunite with Quill's group. The Guardians delay leaving Arête, choosing to rescue the children created by the High Evolutionary, who escape to Knowhere via a tunnel constructed by Cosmo's telekinesis. Rocket discovers imprisoned animals on the ship before being confronted by the High Evolutionary, whom the other Guardians defeat. Rocket spares the High Evolutionary,[d] and the Guardians help the animals escape to Knowhere. Quill nearly dies trying to cross over, but is saved by Adam, who was saved from Arête by Groot as \"everyone deserves a second chance\".\n" +
                        "\n" +
                        "In the end, Quill decides to leave the Guardians, naming Rocket as captain, and travels to Earth to reunite with Jason, his grandfather. Mantis embarks on a journey of self-discovery with the Abilisks, Gamora rejoins the Ravagers, and Nebula and Drax remain on Knowhere to raise the rescued children.",
                backdropPathUrl = "",
                releaseDate = "05/05/2023",
                voteAverage = 9.4
            )
        }

        Poster.Avengers -> {
            MovieDetails(
                id = 2,
                title = "The Avengers",
                genres = listOf("Action", "Adventure"),
                overview = "The Asgardian Loki encounters the Other, the leader of an extraterrestrial race known as the Chitauri. In exchange for retrieving the Tesseract,[c] a powerful energy source of unknown potential, the Other promises Loki an army with which he can subjugate Earth. Nick Fury, director of the espionage agency S.H.I.E.L.D., arrives at a remote research facility, where physicist Dr. Erik Selvig is leading a team experimenting on the Tesseract. The Tesseract suddenly activates and opens a wormhole, allowing Loki to reach Earth. Loki steals the Tesseract and uses his scepter to enslave Selvig and other agents, including Clint Barton, to aid him.\n" +
                        "\n" +
                        "In response, Fury reactivates the \"Avengers Initiative\". Agent Natasha Romanoff heads to Kolkata to recruit Dr. Bruce Banner to trace the Tesseract through its gamma radiation emissions. Fury approaches Steve Rogers to retrieve the Tesseract, and Agent Phil Coulson visits Tony Stark to have him check Selvig's research. Loki is in Stuttgart, where Barton steals the iridium needed to stabilize the Tesseract's power, leading to a confrontation with Rogers, Stark, and Romanoff that ends with Loki's surrender. While Loki gets escorted to S.H.I.E.L.D., his adoptive brother Thor arrives and frees him, hoping to convince him to abandon his plan and return to Asgard. Stark and Rogers intervene and Loki is taken to S.H.I.E.L.D.'s flying aircraft carrier, the Helicarrier, where he is imprisoned.\n" +
                        "\n" +
                        "The Avengers become divided over how to approach Loki and the revelation that S.H.I.E.L.D. plans to harness the Tesseract to develop powerful weapons as a deterrent against hostile extraterrestrials. As they argue, Loki's agents attack the Helicarrier, and the stress causes Banner to transform into the Hulk. Stark and Rogers work to restart the damaged engine, and Thor attempts to stop the Hulk's rampage. Romanoff knocks Barton unconscious, breaking Loki's mind control. Loki escapes after killing Coulson and Fury uses Coulson's death to motivate the Avengers into working as a team. Loki uses the Tesseract and a wormhole generator Selvig built to open a wormhole above Stark Tower to the Chitauri fleet in space, launching his invasion.\n" +
                        "\n" +
                        "Rogers, Stark, Romanoff, Barton, Thor, and the Hulk rally in defense of New York City, and together the Avengers battle the Chitauri. The Hulk beats Loki into submission. Romanoff makes her way to the generator, where Selvig, freed from Loki's mind control, reveals that Loki's scepter can shut down the generator. Fury's superiors from the World Security Council attempt to end the invasion by launching a nuclear missile at Midtown Manhattan. Stark intercepts the missile and takes it through the wormhole toward the Chitauri fleet. The missile detonates, destroying the Chitauri mothership and disabling their forces on Earth. Stark's suit loses power and he goes into freefall, but the Hulk saves him, while Romanoff uses Loki's scepter to close the wormhole. In the aftermath, Thor returns with Loki and the Tesseract to Asgard, where Loki will face their justice.",
                backdropPathUrl = "",
                releaseDate = "04/05/2012",
                voteAverage = 8.7
            )
        }
    }

    sealed class Poster {
        object GuardiansOfTheGalaxy : Poster()
        object Avengers : Poster()
    }
}