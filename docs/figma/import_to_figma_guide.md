# How to Import Mockups and Layouts to Figma

Figma supports multiple import paths depending on whether you want to use the generated high-fidelity visual mockups directly, capture your live frontend code, or paste vector graphics. Here are the three most efficient methods:

---

## Method 1: HTML-to-Figma Vector Capture (Recommended)
Since we have already configured and compiled the Angular application, you can run the app locally and capture the actual running components as fully editable vector layers in Figma.

### 📋 Steps:
1. Run your Angular development server:
   ```bash
   npm run start
   ```
2. Open your browser and navigate to `http://localhost:4200/plans` or the active page.
3. Install the **html.to.design** plugin in Figma (Community $\rightarrow$ Search "html.to.design").
4. Open the plugin inside your Figma file, paste the URL `http://localhost:4200/plans`, and click **Import**.
5. The plugin will instantly convert the live HTML elements, typography, and glassmorphism styles into Figma frames, auto-layouts, and text layers.

---

## Method 2: Copy-Paste Raw SVG Vectors
Figma allows you to copy any valid SVG code as text and paste it directly (`Ctrl + V` or `Cmd + V`) onto the canvas. It will automatically parse the code and render editable vector shapes and texts.

### 📋 Example: Copy and Paste this Glassmorphism Card Vector:
Highlight and copy the XML block below, then paste it directly into Figma:

```xml
<svg width="340" height="480" viewBox="0 0 340 480" fill="none" xmlns="http://www.w3.org/2000/svg">
  <!-- Card Background with Glassmorphic Gradient Fill -->
  <rect x="0.5" y="0.5" width="339" height="479" rx="15.5" fill="#121422" fill-opacity="0.75" stroke="white" stroke-opacity="0.08" stroke-width="1"/>
  
  <!-- Subtle Glowing Core (Indigo/Violet) -->
  <circle cx="170" cy="80" r="60" fill="#6366F1" fill-opacity="0.15" filter="blur(40px)"/>
  
  <!-- Card Title -->
  <text fill="white" font-family="Inter, sans-serif" font-size="20" font-weight="bold" x="30" y="50">Standard Plan</text>
  
  <!-- Card Price -->
  <text fill="#A855F7" font-family="Inter, sans-serif" font-size="28" font-weight="900" x="30" y="95">$14.99</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="14" x="145" y="95">/ month</text>
  
  <!-- Divider -->
  <line x1="30" y1="120" x2="310" y2="120" stroke="white" stroke-opacity="0.08" stroke-width="1"/>
  
  <!-- Checklist Entitlements -->
  <text fill="white" font-family="Inter, sans-serif" font-size="13" x="60" y="160">✓  Full HD Content Access</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" x="60" y="195">✓  Stream on 4 Devices Simultaneously</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" x="60" y="230">✓  Offline Content Downloads Allowed</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="60" y="265">✗  Ultra 4K Stream Access</text>
  
  <!-- Glowing Action Button -->
  <g filter="drop-shadow(0px 4px 10px rgba(99, 102, 241, 0.3))">
    <rect x="30" y="390" width="280" height="50" rx="25" fill="#6366F1"/>
    <text fill="white" font-family="Inter, sans-serif" font-size="15" font-weight="bold" text-anchor="middle" x="170" y="420">Subscribe Now</text>
  </g>
</svg>
```

---

## Method 3: Drag and Drop Image Templates
You can use the high-fidelity mockups generated in the previous step directly for interactive prototyping or tracing.

### 📋 Steps:
1. Open Figma and navigate to your active frame workspace.
2. Open File Explorer to your project's figma documentation folder:
   - `mediahub-frontend/docs/figma/`
3. Drag and drop these generated images directly onto the Figma canvas:
   * **Plan Catalog**: `plan_catalog_1785072669781.jpg`
   * **My Subscriptions**: `my_subscriptions_1785072715446.jpg`
   * **Admin Dashboard**: `admin_dashboard_1785072694169.jpg`
4. Set the frame size to `1440 x 900` or `1920 x 1080` and adjust the images to fit. You can now use Figma's **"Prototype"** tab to draw hotspots (interaction squares) over the buttons to transition between the images, creating a high-fidelity slideshow demo for your Scrum Master!
