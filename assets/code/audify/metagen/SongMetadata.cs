[Serializable]
public class SongMetadata
{
    public string filePath;
    public string title;
    public string artist;
    public string source;

    public SongMetadata(string path, string title, string artist, string source)
    {
        this.filePath = path;
        this.title = title;
        this.artist = artist;
        this.source = source;
    }
}