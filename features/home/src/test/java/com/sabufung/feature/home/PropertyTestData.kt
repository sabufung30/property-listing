package com.sabufung.feature.home

import com.sabufung.domain.model.property.Property
import com.sabufung.domain.model.property.PropertyAddress

object PropertyTestData {

    private val sampleImages = listOf(
        "https://media2.homegate.ch/listings/heia/104123262/image/6b53db714891bfe2321cc3a6d4af76e1.jpg",
        "https://media2.homegate.ch/listings/heia/104123262/image/328c41c0c0805299f5c28d680fbac4d9.jpg",
        "https://media2.homegate.ch/listings/heia/104123262/image/2333f298be7cc3609daaaf2e39e91bf9.jpg",
        "https://media2.homegate.ch/listings/heia/104123262/image/8944c80cb8afb8d5d579ca4faf7dbbb4.jpg",
        "https://media2.homegate.ch/listings/heia/104123262/image/bbb5b2fb8a1cf58ce690e0cfca23d266.jpg",
        "https://media2.homegate.ch/listings/heia/104123262/document/8d844358c73b10cc50c322591a1405c4.pdf",
        "https://media2.homegate.ch/listings/heia/104123262/image/9ed8163e3a9252f7eed28d5212bc1b11.jpg",
    )

    fun property() = listOf(
        Property(id = "104123262",
            title = "LuxuriÃ¶ses Einfamilienhaus mit Pool - Musterinserat",
            images = sampleImages,
            price = 9999999.0,
            address = PropertyAddress(
                location = "La BrÃ©vine",
                region = "NE",
                country = "CH",
                street = "Musterstrasse 999",
                postalCode = "2406"
            ),
            isBookmarked = false),
        Property(id = "3001737958",
            title = "Maison moderne - Annonce exemplaire",
            images = sampleImages,
            price = 9999999.0,
            address = PropertyAddress(
                location = "La BrÃ©vine",
                region = "NE",
                country = "CH",
                street = "Musterstrasse 999",
                postalCode = "2406"
            ),
            isBookmarked = false),
        Property(id = "3001738027",
            title = "Grande maison en viager occupÃ© sans rente",
            images = sampleImages,
            price = 9999999.0,
            address = PropertyAddress(
                location = "La BrÃ©vine",
                region = "NE",
                country = "CH",
                street = "Musterstrasse 999",
                postalCode = "2406"
            ),
            isBookmarked = false),

    )

}