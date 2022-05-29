package es.uji.al394716.musicapp.Entities

import android.graphics.Bitmap
import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Song(@PrimaryKey val idSong : Int,
           val title:String?,
           val artistSongName:String?,
           val date:String?,
           val image:String?,
           val fullTitle:String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(idSong)
        p0.writeString(title)
        p0.writeString(artistSongName)
        p0.writeString(date)
        p0.writeString(image)
        p0.writeString(fullTitle)
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