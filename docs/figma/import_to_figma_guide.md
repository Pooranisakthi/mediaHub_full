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

---

## 🛠️ Complete Copy-Paste SVG Code Library for Figma

Here are all the layout frames for the interactive presentation flow. Open this file in a text editor (like Notepad) on your work laptop, and copy the SVG blocks to paste directly into Figma.

### Screen 2: Subscription Enrollment Frame (SPM-SUB-02)
```xml
<svg width="800" height="600" viewBox="0 0 800 600" fill="none" xmlns="http://www.w3.org/2000/svg">
  <rect width="800" height="600" rx="8" fill="#0A0B10"/>
  <rect width="800" height="60" fill="#121422"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="18" font-weight="bold" x="30" y="36">MediaHub Checkout</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="14" x="720" y="36">Cancel</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="20" font-weight="bold" x="50" y="110">Enrollment Details</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="50" y="130">Provide billing preferences to activate your entitlements</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="50" y="180">Billing Cycle</text>
  <rect x="50" y="195" width="340" height="45" rx="6" fill="#121422" stroke="white" stroke-opacity="0.08" stroke-width="1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="14" x="65" y="222">Monthly ($14.99 / month)</text>
  <path d="M360 215L365 220L370 215" stroke="white" stroke-width="1.5"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="50" y="270">Renewal Mode</text>
  <rect x="50" y="285" width="340" height="45" rx="6" fill="#121422" stroke="white" stroke-opacity="0.08" stroke-width="1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="14" x="65" y="312">Auto-Renew Subscription</text>
  <rect x="350" y="298" width="18" height="18" rx="3" fill="#6366F1"/>
  <path d="M354 307L357 310L364 303" stroke="white" stroke-width="2"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="50" y="360">Entitlements Start Date</text>
  <rect x="50" y="375" width="340" height="45" rx="6" fill="#121422" stroke="#6366F1" stroke-width="1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="14" x="65" y="402">Immediate (Today, 26 July 2026)</text>
  <rect x="440" y="110" width="310" height="340" rx="12" fill="#121422" stroke="white" stroke-opacity="0.08" stroke-width="1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="16" font-weight="bold" x="470" y="150">Order Summary</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="470" y="190">Selected Plan:</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="640" y="190">Standard Tier</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="470" y="225">Entitlement Price:</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="660" y="225">$14.99</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="470" y="260">Taxes &amp; Fees:</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="665" y="260">$0.00</text>
  <line x1="470" y1="285" x2="720" y2="285" stroke="white" stroke-opacity="0.08" stroke-width="1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="15" font-weight="bold" x="470" y="315">Total Due Today:</text>
  <text fill="#6366F1" font-family="Inter, sans-serif" font-size="18" font-weight="900" x="650" y="315">$14.99</text>
  <rect x="470" y="360" width="250" height="50" rx="25" fill="#6366F1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="14" font-weight="bold" text-anchor="middle" x="595" y="390">Activate entitlements</text>
</svg>
```

### Screen 3: My Subscriptions Portal (SPM-SUB-03)
```xml
<svg width="800" height="600" viewBox="0 0 800 600" fill="none" xmlns="http://www.w3.org/2000/svg">
  <rect width="800" height="600" rx="8" fill="#0A0B10"/>
  <rect width="800" height="60" fill="#121422"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="18" font-weight="bold" x="30" y="36">MediaHub Subscriber Portal</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="14" x="680" y="36">My Profile</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="22" font-weight="bold" x="30" y="110">My Subscriptions</text>
  <rect x="30" y="140" width="740" height="150" rx="12" fill="#121422" stroke="white" stroke-opacity="0.08" stroke-width="1"/>
  <rect x="50" y="165" width="20" height="20" rx="10" fill="#10B981"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" font-weight="bold" x="55" y="179">✓</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="18" font-weight="bold" x="80" y="180">Standard Tier Active</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="80" y="200">Entitlements active. Next auto-billing date is 26 August 2026 ($14.99).</text>
  <rect x="80" y="230" width="130" height="35" rx="6" fill="#6366F1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" font-weight="bold" text-anchor="middle" x="145" y="252">Upgrade Plan</text>
  <rect x="225" y="230" width="150" height="35" rx="6" fill="#1E293B" stroke="white" stroke-opacity="0.1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" text-anchor="middle" x="300" y="252">Cancel Subscription</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="16" font-weight="bold" x="30" y="340">Billing History</text>
  <rect x="30" y="365" width="740" height="45" rx="6" fill="#121422" fill-opacity="0.5"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" x="50" y="392">Invoice #MH-87352</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="250" y="392">26 July 2026</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="480" y="392">$14.99</text>
  <text fill="#10B981" font-family="Inter, sans-serif" font-size="13" x="600" y="392">Paid</text>
  <text fill="#6366F1" font-family="Inter, sans-serif" font-size="13" x="700" y="392">Download PDF</text>
  <rect x="30" y="420" width="740" height="45" rx="6" fill="#121422" fill-opacity="0.5"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" x="50" y="447">Invoice #MH-65412</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="13" x="250" y="447">26 June 2026</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="13" font-weight="bold" x="480" y="447">$14.99</text>
  <text fill="#10B981" font-family="Inter, sans-serif" font-size="13" x="600" y="447">Paid</text>
  <text fill="#6366F1" font-family="Inter, sans-serif" font-size="13" x="700" y="447">Download PDF</text>
</svg>
```

### Screen 4: Admin Plan Configuration Dashboard (SPM-ADM-01)
```xml
<svg width="800" height="600" viewBox="0 0 800 600" fill="none" xmlns="http://www.w3.org/2000/svg">
  <rect width="800" height="600" rx="8" fill="#0A0B10"/>
  <rect width="180" height="600" fill="#121422"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="16" font-weight="bold" x="25" y="40">MediaHub Admin</text>
  <text fill="#6366F1" font-family="Inter, sans-serif" font-size="14" font-weight="bold" x="25" y="110">Plan config</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="14" x="25" y="150">Users registry</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="14" x="25" y="190">Content catalog</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="14" x="25" y="230">Audit reports</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="22" font-weight="bold" x="210" y="45">Plan Registry</text>
  <rect x="630" y="22" width="140" height="35" rx="17.5" fill="#6366F1"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" font-weight="bold" text-anchor="middle" x="700" y="44">+ Add New Plan</text>
  <rect x="210" y="80" width="170" height="80" rx="10" fill="#121422" stroke="white" stroke-opacity="0.08"/>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" x="225" y="105">ACTIVE SUBSCRIBERS</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="22" font-weight="900" x="225" y="140">12,450</text>
  <rect x="400" y="80" width="170" height="80" rx="10" fill="#121422" stroke="white" stroke-opacity="0.08"/>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" x="415" y="105">MONTHLY REVENUE</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="22" font-weight="900" x="415" y="140">$182.4K</text>
  <rect x="590" y="80" width="180" height="80" rx="10" fill="#121422" stroke="white" stroke-opacity="0.08"/>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" x="605" y="105">CHURN RATIO</text>
  <text fill="#EF4444" font-family="Inter, sans-serif" font-size="22" font-weight="900" x="605" y="140">2.1%</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="15" font-weight="bold" x="210" y="200">System Subscription Tiers</text>
  <rect x="210" y="220" width="560" height="30" fill="#121422" fill-opacity="0.5"/>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" font-weight="bold" x="225" y="238">PLAN NAME</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" font-weight="bold" x="380" y="238">PRICE</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" font-weight="bold" x="480" y="238">CYCLE</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" font-weight="bold" x="580" y="238">STATUS</text>
  <text fill="#94A3B8" font-family="Inter, sans-serif" font-size="11" font-weight="bold" x="680" y="238">ACTIONS</text>
  <rect x="210" y="260" width="560" height="40" rx="4" fill="#121422" fill-opacity="0.3"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" font-weight="bold" x="225" y="284">Free Tier</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" x="380" y="284">$0.00</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" x="480" y="284">Monthly</text>
  <text fill="#10B981" font-family="Inter, sans-serif" font-size="12" font-weight="bold" x="580" y="284">Active</text>
  <text fill="#6366F1" font-family="Inter, sans-serif" font-size="12" x="680" y="284">Edit • Deact</text>
  <rect x="210" y="310" width="560" height="40" rx="4" fill="#121422" fill-opacity="0.3"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" font-weight="bold" x="225" y="334">Standard Tier</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" x="380" y="334">$14.99</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" x="480" y="334">Monthly</text>
  <text fill="#10B981" font-family="Inter, sans-serif" font-size="12" font-weight="bold" x="580" y="334">Active</text>
  <text fill="#6366F1" font-family="Inter, sans-serif" font-size="12" x="680" y="334">Edit • Deact</text>
  <rect x="210" y="360" width="560" height="40" rx="4" fill="#121422" fill-opacity="0.3"/>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" font-weight="bold" x="225" y="384">Premium Tier</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" x="380" y="384">$19.99</text>
  <text fill="white" font-family="Inter, sans-serif" font-size="12" x="480" y="384">Monthly</text>
  <text fill="#10B981" font-family="Inter, sans-serif" font-size="12" font-weight="bold" x="580" y="384">Active</text>
  <text fill="#6366F1" font-family="Inter, sans-serif" font-size="12" x="680" y="384">Edit • Deact</text>
</svg>
```

