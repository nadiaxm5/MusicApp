package es.uji.al394716.musicapp.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

class Artist(@PrimaryKey val idArtist : Int,
             val idArtistSong : Int,
             val titleArtistSong:String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(idArtist)
        p0.writeInt(idArtistSong)
        p0.writeString(titleArtistSong)
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}