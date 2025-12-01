public class MetadataEditorUI : MonoBehaviour
{
    public InputField titleInput;
    public InputField artistInput;
    public InputField sourceInput;
    public Button saveButton;
    
    private string metadataFolder;
    private string currentJsonPath;
    private SongMetadata currentMetadata;
    
    public delegate void MetadataUpdatedHandler(string jsonPath);
    public static event MetadataUpdatedHandler OnMetadataUpdated;

    private void Start()
    {
        metadataFolder = Path.Combine(Application.streamingAssetsPath, "metadata");
        Directory.CreateDirectory(metadataFolder);
        saveButton.onClick.AddListener(SaveMetadata);
    }

    public void SaveMetadata()
    {
        if (currentMetadata == null || string.IsNullOrEmpty(currentJsonPath))
            return;

        currentMetadata.title = titleInput.text;
        currentMetadata.artist = artistInput.text;
        currentMetadata.source = sourceInput.text;

        string json = JsonUtility.ToJson(currentMetadata, true);
        File.WriteAllText(currentJsonPath, json);

        Debug.Log("Metadata saved for: " + currentMetadata.title);
        
        OnMetadataUpdated?.Invoke(currentJsonPath);
    }
}