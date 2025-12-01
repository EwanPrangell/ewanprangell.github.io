public class MetadataManager : MonoBehaviour
{
    public Text titleText;
    public Text artistText;
    public Text sourceText;

    private string currentSongJsonPath;

    public void ShowSong(string filePath)
    {
        string fileName = Path.GetFileNameWithoutExtension(filePath);
        currentSongJsonPath = Path.Combine(Application.streamingAssetsPath, "metadata", fileName + ".json");
        
        if (!File.Exists(currentSongJsonPath))
        {
            SongMetadata newMetadata = new SongMetadata(
                filePath,
                Path.GetFileNameWithoutExtension(filePath), // use file name as title
                "Unknown Artist",
                "Local"
            );

            string json = JsonUtility.ToJson(newMetadata, true);
            File.WriteAllText(currentSongJsonPath, json);
            Debug.Log("Created new metadata file: " + currentSongJsonPath);
            
            Object.FindFirstObjectByType<MetadataEditorUI>()?.PopulateFileList();

        }
    }
}