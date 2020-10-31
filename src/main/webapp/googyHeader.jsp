<div class="container-fulid">
 	<header class=hero>

        <div class=hero_bg></div>
        <div class=hero_fg></div>
      
      </header>
      <svg class=visually-hidden>
        <defs>
          
          <!-- Lucas Bebber's goo filter -->
          <!-- Unused as it severely reduces the frame rate -->
          <filter id="goo">
            <feGaussianBlur in="SourceGraphic" result="blur" stdDeviation="2" />
            <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 18 -7" result="goo" />
            <feComposite in2="goo" in="SourceGraphic" result="mix" />
          </filter>
      
          <!-- Used on the heading -->
          <linearGradient id="myLinearGradient1"
                          x1="0%" y1="0%"
                          x2="0%" y2="100%"
                          spreadMethod="pad">
            <stop offset="0%" stop-color="#00cc00" stop-opacity="1"/>
            <stop offset="100%" stop-color="#006600" stop-opacity="1"/>
            <animate values="0% 25% 0%;" dur="2s" attributeName="y1" repeatCount="indefinite"></animate>
            <animate values="100%; 50%; 100%" dur="4s" attributeName="y2" repeatCount="indefinite"></animate>
              
          </linearGradient>
      
        </defs>
      </svg>
</div>