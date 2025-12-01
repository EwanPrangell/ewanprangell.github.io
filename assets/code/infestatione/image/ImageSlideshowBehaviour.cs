public class ImageSlideshowBehaviour : MonoBehaviour
{
    //All slides in the project, minus the fadeSlide
    [SerializeField]
    private List<Transform> slides = default;

    //The panel that overlays all slides and changes from clear to black
    [SerializeField]
    private Image fadeSlide = default;

    [Header("Config Values")]
    [SerializeField, Tooltip("The duration (in seconds) over which the fade slide will fade in / out")]
    private float fadeDuration = 0.75f;
    [Header("Config Values")]
    [SerializeField, Tooltip("The duration (in seconds) over switch silde")]
    private float switchDuration = 1.6f;
    //The slide currently viewing
    private int currentSlide = -1;

    // Whether the fade slide is currently fading
    private IEnumerator Start()
    {
        // Set the fade to black slide to black so that the audience can not see the first slide
        fadeSlide.color = Color.black;
        while (true)
        {
            currentSlide++;
            currentSlide = currentSlide % slides.Count;

            // Transition to the next slide
            StartCoroutine(SlideTransition());
            yield return new WaitForSeconds(switchDuration);
        }
    }

    private IEnumerator SlideTransition()
    {
        // Fade to black
        yield return StartCoroutine(FadeToTargetColor(targetColor: Color.black));

        // Set only the current slide active - and all others inactive
        slides.ForEach(slide => slide.gameObject.SetActive(slides.IndexOf(slide) == currentSlide));

        // Fade to clear
        yield return StartCoroutine(FadeToTargetColor(targetColor: Color.clear));
    }

    private IEnumerator FadeToTargetColor(Color targetColor)
    {
        // The total amount of seconds that has elapsed since the start of the lerp sequence
        float elapsedTime = 0.0f;

        // The color of the fade panel at the start of the lerp sequence
        Color startColor = fadeSlide.color;

        // While it hasn't reached the end of the lerp sequence..
        while (elapsedTime < fadeDuration)
        {
            // Increase the elapsed time
            elapsedTime += Time.deltaTime;

            // Perform a lerp to the target color
            fadeSlide.color = Color.Lerp(startColor, targetColor, elapsedTime / fadeDuration);

            // Wait for the next frame
            yield return null;
        }
    }
}