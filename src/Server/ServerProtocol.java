




<!DOCTYPE html>
<html>
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# object: http://ogp.me/ns/object# article: http://ogp.me/ns/article# profile: http://ogp.me/ns/profile#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>rolit/src/rolit/model/networking/server/ServerProtocol.java at beaa86442a3902728118e6e5240b97da2fdca2ae · niomaster/rolit</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub" />
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub" />
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png" />
    <meta property="fb:app_id" content="1401488693436528"/>
    <meta content="@github" name="twitter:site" /><meta content="summary" name="twitter:card" /><meta content="niomaster/rolit" name="twitter:title" /><meta content="Contribute to rolit development by creating an account on GitHub." name="twitter:description" /><meta content="https://2.gravatar.com/avatar/30bed2d992d7e10854e6743f35e770ef?d=https%3A%2F%2Fidenticons.github.com%2Ffac9fa0a65c9b884d6a3b23850ed6733.png&amp;r=x&amp;s=400" name="twitter:image:src" />
<meta content="GitHub" property="og:site_name" /><meta content="object" property="og:type" /><meta content="https://2.gravatar.com/avatar/30bed2d992d7e10854e6743f35e770ef?d=https%3A%2F%2Fidenticons.github.com%2Ffac9fa0a65c9b884d6a3b23850ed6733.png&amp;r=x&amp;s=400" property="og:image" /><meta content="niomaster/rolit" property="og:title" /><meta content="https://github.com/niomaster/rolit" property="og:url" /><meta content="Contribute to rolit development by creating an account on GitHub." property="og:description" />

    <meta name="hostname" content="github-fe120-cp1-prd.iad.github.net">
    <meta name="ruby" content="ruby 2.1.0p0-github-tcmalloc (60139581e1) [x86_64-linux]">
    <link rel="assets" href="https://github.global.ssl.fastly.net/">
    <link rel="conduit-xhr" href="https://ghconduit.com:25035/">
    <link rel="xhr-socket" href="/_sockets" />
    


    <meta name="msapplication-TileImage" content="/windows-tile.png" />
    <meta name="msapplication-TileColor" content="#ffffff" />
    <meta name="selected-link" value="repo_source" data-pjax-transient />
    <meta content="collector.githubapp.com" name="octolytics-host" /><meta content="collector-cdn.github.com" name="octolytics-script-host" /><meta content="github" name="octolytics-app-id" /><meta content="8259B523:6646:A1C2CCC:52E79EAB" name="octolytics-dimension-request_id" /><meta content="5594436" name="octolytics-actor-id" /><meta content="cmitz" name="octolytics-actor-login" /><meta content="98d019d961762dcfdce193c01d82bb7b6c3dd8302805f5e3b2557d09ed99182c" name="octolytics-actor-hash" />
    

    
    
    <link rel="icon" type="image/x-icon" href="/favicon.ico" />

    <meta content="authenticity_token" name="csrf-param" />
<meta content="NFOeAdb34wgo4yEBFzEEXZ5gbQlGrpTDmTmk0VOnKto=" name="csrf-token" />

    <link href="https://github.global.ssl.fastly.net/assets/github-c9ee421fa9d0fd6a5397695acc603b658728d2d5.css" media="all" rel="stylesheet" type="text/css" />
    <link href="https://github.global.ssl.fastly.net/assets/github2-10f7b6999ba0543eaa86b9fe582445f0ee747741.css" media="all" rel="stylesheet" type="text/css" />
    


      <script src="https://github.global.ssl.fastly.net/assets/frameworks-2086b76396d7018acf390457357b14121f3e16f3.js" type="text/javascript"></script>
      <script async="async" defer="defer" src="https://github.global.ssl.fastly.net/assets/github-4f3fea02ac13c7f75f0e8530e0996e90c6e78194.js" type="text/javascript"></script>
      
      <meta http-equiv="x-pjax-version" content="ef405d9ceb05f99bced72d97f4e83cac">

        <link data-pjax-transient rel='permalink' href='/niomaster/rolit/blob/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit/model/networking/server/ServerProtocol.java'>

  <meta name="description" content="Contribute to rolit development by creating an account on GitHub." />

  <meta content="901022" name="octolytics-dimension-user_id" /><meta content="niomaster" name="octolytics-dimension-user_login" /><meta content="15776158" name="octolytics-dimension-repository_id" /><meta content="niomaster/rolit" name="octolytics-dimension-repository_nwo" /><meta content="true" name="octolytics-dimension-repository_public" /><meta content="false" name="octolytics-dimension-repository_is_fork" /><meta content="15776158" name="octolytics-dimension-repository_network_root_id" /><meta content="niomaster/rolit" name="octolytics-dimension-repository_network_root_nwo" />
  <link href="https://github.com/niomaster/rolit/commits/beaa86442a3902728118e6e5240b97da2fdca2ae.atom" rel="alternate" title="Recent Commits to rolit:beaa86442a3902728118e6e5240b97da2fdca2ae" type="application/atom+xml" />

  </head>


  <body class="logged_in  env-production windows vis-public page-blob">
    <div class="wrapper">
      
      
      
      


      <div class="header header-logged-in true">
  <div class="container clearfix">

    <a class="header-logo-invertocat" href="https://github.com/">
  <span class="mega-octicon octicon-mark-github"></span>
</a>

    
    <a href="/notifications" class="notification-indicator tooltipped downwards" data-gotokey="n" title="You have no unread notifications">
        <span class="mail-status all-read"></span>
</a>

      <div class="command-bar js-command-bar  in-repository">
          <form accept-charset="UTF-8" action="/search" class="command-bar-form" id="top_search_form" method="get">

<input type="text" data-hotkey="/ s" name="q" id="js-command-bar-field" placeholder="Search or type a command" tabindex="1" autocapitalize="off"
    
    data-username="cmitz"
      data-repo="niomaster/rolit"
      data-branch="beaa86442a3902728118e6e5240b97da2fdca2ae"
      data-sha="913d40bc3c4da6c4356c5d1de1b8730831e39ae4"
  >

    <input type="hidden" name="nwo" value="niomaster/rolit" />

    <div class="select-menu js-menu-container js-select-menu search-context-select-menu">
      <span class="minibutton select-menu-button js-menu-target">
        <span class="js-select-button">This repository</span>
      </span>

      <div class="select-menu-modal-holder js-menu-content js-navigation-container">
        <div class="select-menu-modal">

          <div class="select-menu-item js-navigation-item js-this-repository-navigation-item selected">
            <span class="select-menu-item-icon octicon octicon-check"></span>
            <input type="radio" class="js-search-this-repository" name="search_target" value="repository" checked="checked" />
            <div class="select-menu-item-text js-select-button-text">This repository</div>
          </div> <!-- /.select-menu-item -->

          <div class="select-menu-item js-navigation-item js-all-repositories-navigation-item">
            <span class="select-menu-item-icon octicon octicon-check"></span>
            <input type="radio" name="search_target" value="global" />
            <div class="select-menu-item-text js-select-button-text">All repositories</div>
          </div> <!-- /.select-menu-item -->

        </div>
      </div>
    </div>

  <span class="octicon help tooltipped downwards" title="Show command bar help">
    <span class="octicon octicon-question"></span>
  </span>


  <input type="hidden" name="ref" value="cmdform">

</form>
        <ul class="top-nav">
          <li class="explore"><a href="/explore">Explore</a></li>
            <li><a href="https://gist.github.com">Gist</a></li>
            <li><a href="/blog">Blog</a></li>
          <li><a href="https://help.github.com">Help</a></li>
        </ul>
      </div>

    


  <ul id="user-links">
    <li>
      <a href="/cmitz" class="name">
        <img alt="cmitz" height="20" src="https://0.gravatar.com/avatar/689508dab9a97962694ee932b351e3be?d=https%3A%2F%2Fidenticons.github.com%2Fa9e33bdae4f7192a0be0d35f21e6a7ab.png&amp;r=x&amp;s=140" width="20" /> cmitz
      </a>
    </li>

    <li class="new-menu dropdown-toggle js-menu-container">
      <a href="#" class="js-menu-target tooltipped downwards" title="Create new..." aria-label="Create new...">
        <span class="octicon octicon-plus"></span>
        <span class="dropdown-arrow"></span>
      </a>

      <div class="js-menu-content">
      </div>
    </li>

    <li>
      <a href="/settings/profile" id="account_settings"
        class="tooltipped downwards"
        aria-label="Account settings "
        title="Account settings ">
        <span class="octicon octicon-tools"></span>
      </a>
    </li>
    <li>
      <a class="tooltipped downwards" href="/logout" data-method="post" id="logout" title="Sign out" aria-label="Sign out">
        <span class="octicon octicon-log-out"></span>
      </a>
    </li>

  </ul>

<div class="js-new-dropdown-contents hidden">
  

<ul class="dropdown-menu">
  <li>
    <a href="/new"><span class="octicon octicon-repo-create"></span> New repository</a>
  </li>
  <li>
    <a href="/organizations/new"><span class="octicon octicon-organization"></span> New organization</a>
  </li>



    <li class="section-title">
      <span title="niomaster/rolit">This repository</span>
    </li>
      <li>
        <a href="/niomaster/rolit/issues/new"><span class="octicon octicon-issue-opened"></span> New issue</a>
      </li>
</ul>

</div>


    
  </div>
</div>

      

      




          <div class="site" itemscope itemtype="http://schema.org/WebPage">
    
    <div class="pagehead repohead instapaper_ignore readability-menu">
      <div class="container">
        

<ul class="pagehead-actions">

    <li class="subscription">
      <form accept-charset="UTF-8" action="/notifications/subscribe" class="js-social-container" data-autosubmit="true" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="authenticity_token" type="hidden" value="NFOeAdb34wgo4yEBFzEEXZ5gbQlGrpTDmTmk0VOnKto=" /></div>  <input id="repository_id" name="repository_id" type="hidden" value="15776158" />

    <div class="select-menu js-menu-container js-select-menu">
      <a class="social-count js-social-count" href="/niomaster/rolit/watchers">
        4
      </a>
      <span class="minibutton select-menu-button with-count js-menu-target" role="button" tabindex="0">
        <span class="js-select-button">
          <span class="octicon octicon-eye-unwatch"></span>
          Unwatch
        </span>
      </span>

      <div class="select-menu-modal-holder">
        <div class="select-menu-modal subscription-menu-modal js-menu-content">
          <div class="select-menu-header">
            <span class="select-menu-title">Notification status</span>
            <span class="octicon octicon-remove-close js-menu-close"></span>
          </div> <!-- /.select-menu-header -->

          <div class="select-menu-list js-navigation-container" role="menu">

            <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input id="do_included" name="do" type="radio" value="included" />
                <h4>Not watching</h4>
                <span class="description">You only receive notifications for conversations in which you participate or are @mentioned.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-eye-watch"></span>
                  Watch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item selected" role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input checked="checked" id="do_subscribed" name="do" type="radio" value="subscribed" />
                <h4>Watching</h4>
                <span class="description">You receive notifications for all conversations in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-eye-unwatch"></span>
                  Unwatch
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

            <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <div class="select-menu-item-text">
                <input id="do_ignore" name="do" type="radio" value="ignore" />
                <h4>Ignoring</h4>
                <span class="description">You do not receive any notifications for conversations in this repository.</span>
                <span class="js-select-button-text hidden-select-button-text">
                  <span class="octicon octicon-mute"></span>
                  Stop ignoring
                </span>
              </div>
            </div> <!-- /.select-menu-item -->

          </div> <!-- /.select-menu-list -->

        </div> <!-- /.select-menu-modal -->
      </div> <!-- /.select-menu-modal-holder -->
    </div> <!-- /.select-menu -->

</form>
    </li>

  <li>
  

  <div class="js-toggler-container js-social-container starring-container ">
    <a href="/niomaster/rolit/unstar"
      class="minibutton with-count js-toggler-target star-button starred upwards"
      title="Unstar this repository" data-remote="true" data-method="post" rel="nofollow">
      <span class="octicon octicon-star-delete"></span><span class="text">Unstar</span>
    </a>

    <a href="/niomaster/rolit/star"
      class="minibutton with-count js-toggler-target star-button unstarred upwards"
      title="Star this repository" data-remote="true" data-method="post" rel="nofollow">
      <span class="octicon octicon-star"></span><span class="text">Star</span>
    </a>

      <a class="social-count js-social-count" href="/niomaster/rolit/stargazers">
        0
      </a>
  </div>

  </li>


        <li>
          <a href="/niomaster/rolit/fork" class="minibutton with-count js-toggler-target fork-button lighter upwards" title="Fork this repo" rel="nofollow" data-method="post">
            <span class="octicon octicon-git-branch-create"></span><span class="text">Fork</span>
          </a>
          <a href="/niomaster/rolit/network" class="social-count">1</a>
        </li>


</ul>

        <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public">
          <span class="repo-label"><span>public</span></span>
          <span class="mega-octicon octicon-repo"></span>
          <span class="author">
            <a href="/niomaster" class="url fn" itemprop="url" rel="author"><span itemprop="title">niomaster</span></a>
          </span>
          <span class="repohead-name-divider">/</span>
          <strong><a href="/niomaster/rolit" class="js-current-repository js-repo-home-link">rolit</a></strong>

          <span class="page-context-loader">
            <img alt="Octocat-spinner-32" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
          </span>

        </h1>
      </div><!-- /.container -->
    </div><!-- /.repohead -->

    <div class="container">
      

      <div class="repository-with-sidebar repo-container  ">

        <div class="repository-sidebar">
            

<div class="sunken-menu vertical-right repo-nav js-repo-nav js-repository-container-pjax js-octicon-loaders">
  <div class="sunken-menu-contents">
    <ul class="sunken-menu-group">
      <li class="tooltipped leftwards" title="Code">
        <a href="/niomaster/rolit" aria-label="Code" class="selected js-selected-navigation-item sunken-menu-item" data-gotokey="c" data-pjax="true" data-selected-links="repo_source repo_downloads repo_commits repo_tags repo_branches /niomaster/rolit">
          <span class="octicon octicon-code"></span> <span class="full-word">Code</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

        <li class="tooltipped leftwards" title="Issues">
          <a href="/niomaster/rolit/issues" aria-label="Issues" class="js-selected-navigation-item sunken-menu-item js-disable-pjax" data-gotokey="i" data-selected-links="repo_issues /niomaster/rolit/issues">
            <span class="octicon octicon-issue-opened"></span> <span class="full-word">Issues</span>
            <span class='counter'>1</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>        </li>

      <li class="tooltipped leftwards" title="Pull Requests">
        <a href="/niomaster/rolit/pulls" aria-label="Pull Requests" class="js-selected-navigation-item sunken-menu-item js-disable-pjax" data-gotokey="p" data-selected-links="repo_pulls /niomaster/rolit/pulls">
            <span class="octicon octicon-git-pull-request"></span> <span class="full-word">Pull Requests</span>
            <span class='counter'>0</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>


        <li class="tooltipped leftwards" title="Wiki">
          <a href="/niomaster/rolit/wiki" aria-label="Wiki" class="js-selected-navigation-item sunken-menu-item" data-pjax="true" data-selected-links="repo_wiki /niomaster/rolit/wiki">
            <span class="octicon octicon-book"></span> <span class="full-word">Wiki</span>
            <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>        </li>
    </ul>
    <div class="sunken-menu-separator"></div>
    <ul class="sunken-menu-group">

      <li class="tooltipped leftwards" title="Pulse">
        <a href="/niomaster/rolit/pulse" aria-label="Pulse" class="js-selected-navigation-item sunken-menu-item" data-pjax="true" data-selected-links="pulse /niomaster/rolit/pulse">
          <span class="octicon octicon-pulse"></span> <span class="full-word">Pulse</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

      <li class="tooltipped leftwards" title="Graphs">
        <a href="/niomaster/rolit/graphs" aria-label="Graphs" class="js-selected-navigation-item sunken-menu-item" data-pjax="true" data-selected-links="repo_graphs repo_contributors /niomaster/rolit/graphs">
          <span class="octicon octicon-graph"></span> <span class="full-word">Graphs</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>

      <li class="tooltipped leftwards" title="Network">
        <a href="/niomaster/rolit/network" aria-label="Network" class="js-selected-navigation-item sunken-menu-item js-disable-pjax" data-selected-links="repo_network /niomaster/rolit/network">
          <span class="octicon octicon-git-branch"></span> <span class="full-word">Network</span>
          <img alt="Octocat-spinner-32" class="mini-loader" height="16" src="https://github.global.ssl.fastly.net/images/spinners/octocat-spinner-32.gif" width="16" />
</a>      </li>
    </ul>


  </div>
</div>

              <div class="only-with-full-nav">
                

  

<div class="clone-url open"
  data-protocol-type="http"
  data-url="/users/set_protocol?protocol_selector=http&amp;protocol_type=clone">
  <h3><strong>HTTPS</strong> clone URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="https://github.com/niomaster/rolit.git" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="https://github.com/niomaster/rolit.git" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>

  

<div class="clone-url "
  data-protocol-type="ssh"
  data-url="/users/set_protocol?protocol_selector=ssh&amp;protocol_type=clone">
  <h3><strong>SSH</strong> clone URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="git@github.com:niomaster/rolit.git" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="git@github.com:niomaster/rolit.git" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>

  

<div class="clone-url "
  data-protocol-type="subversion"
  data-url="/users/set_protocol?protocol_selector=subversion&amp;protocol_type=clone">
  <h3><strong>Subversion</strong> checkout URL</h3>
  <div class="clone-url-box">
    <input type="text" class="clone js-url-field"
           value="https://github.com/niomaster/rolit" readonly="readonly">

    <span class="js-zeroclipboard url-box-clippy minibutton zeroclipboard-button" data-clipboard-text="https://github.com/niomaster/rolit" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>


<p class="clone-options">You can clone with
      <a href="#" class="js-clone-selector" data-protocol="http">HTTPS</a>,
      <a href="#" class="js-clone-selector" data-protocol="ssh">SSH</a>,
      or <a href="#" class="js-clone-selector" data-protocol="subversion">Subversion</a>.
  <span class="octicon help tooltipped upwards" title="Get help on which URL is right for you.">
    <a href="https://help.github.com/articles/which-remote-url-should-i-use">
    <span class="octicon octicon-question"></span>
    </a>
  </span>
</p>


  <a href="github-windows://openRepo/https://github.com/niomaster/rolit" class="minibutton sidebar-button">
    <span class="octicon octicon-device-desktop"></span>
    Clone in Desktop
  </a>

                <a href="/niomaster/rolit/archive/beaa86442a3902728118e6e5240b97da2fdca2ae.zip"
                   class="minibutton sidebar-button"
                   title="Download this repository as a zip file"
                   rel="nofollow">
                  <span class="octicon octicon-cloud-download"></span>
                  Download ZIP
                </a>
              </div>
        </div><!-- /.repository-sidebar -->

        <div id="js-repo-pjax-container" class="repository-content context-loader-container" data-pjax-container>
          


<!-- blob contrib key: blob_contributors:v21:21381fee958985aa26898b14c67c20d9 -->

<p title="This is a placeholder element" class="js-history-link-replace hidden"></p>

<a href="/niomaster/rolit/find/beaa86442a3902728118e6e5240b97da2fdca2ae" data-pjax data-hotkey="t" class="js-show-file-finder" style="display:none">Show File Finder</a>

<div class="file-navigation">
  

<div class="select-menu js-menu-container js-select-menu" >
  <span class="minibutton select-menu-button js-menu-target" data-hotkey="w"
    data-master-branch="master"
    data-ref=""
    role="button" aria-label="Switch branches or tags" tabindex="0">
    <span class="octicon octicon-git-branch"></span>
    <i>tree:</i>
    <span class="js-select-button">beaa86442a</span>
  </span>

  <div class="select-menu-modal-holder js-menu-content js-navigation-container" data-pjax>

    <div class="select-menu-modal">
      <div class="select-menu-header">
        <span class="select-menu-title">Switch branches/tags</span>
        <span class="octicon octicon-remove-close js-menu-close"></span>
      </div> <!-- /.select-menu-header -->

      <div class="select-menu-filters">
        <div class="select-menu-text-filter">
          <input type="text" aria-label="Filter branches/tags" id="context-commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Filter branches/tags">
        </div>
        <div class="select-menu-tabs">
          <ul>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="branches" class="js-select-menu-tab">Branches</a>
            </li>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="tags" class="js-select-menu-tab">Tags</a>
            </li>
          </ul>
        </div><!-- /.select-menu-tabs -->
      </div><!-- /.select-menu-filters -->

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="branches">

        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/niomaster/rolit/blob/client-handler/src/rolit/model/networking/server/ServerProtocol.java"
                 data-name="client-handler"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="client-handler">client-handler</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/niomaster/rolit/blob/martijn/src/rolit/model/networking/server/ServerProtocol.java"
                 data-name="martijn"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="martijn">martijn</a>
            </div> <!-- /.select-menu-item -->
            <div class="select-menu-item js-navigation-item ">
              <span class="select-menu-item-icon octicon octicon-check"></span>
              <a href="/niomaster/rolit/blob/master/src/rolit/model/networking/server/ServerProtocol.java"
                 data-name="master"
                 data-skip-pjax="true"
                 rel="nofollow"
                 class="js-navigation-open select-menu-item-text js-select-button-text css-truncate-target"
                 title="master">master</a>
            </div> <!-- /.select-menu-item -->
        </div>

          <div class="select-menu-no-results">Nothing to show</div>
      </div> <!-- /.select-menu-list -->

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="tags">
        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


        </div>

        <div class="select-menu-no-results">Nothing to show</div>
      </div> <!-- /.select-menu-list -->

    </div> <!-- /.select-menu-modal -->
  </div> <!-- /.select-menu-modal-holder -->
</div> <!-- /.select-menu -->

  <div class="breadcrumb">
    <span class='repo-root js-repo-root'><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/niomaster/rolit/tree/beaa86442a3902728118e6e5240b97da2fdca2ae" data-branch="beaa86442a3902728118e6e5240b97da2fdca2ae" data-direction="back" data-pjax="true" itemscope="url" rel="nofollow"><span itemprop="title">rolit</span></a></span></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/niomaster/rolit/tree/beaa86442a3902728118e6e5240b97da2fdca2ae/src" data-branch="beaa86442a3902728118e6e5240b97da2fdca2ae" data-direction="back" data-pjax="true" itemscope="url" rel="nofollow"><span itemprop="title">src</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/niomaster/rolit/tree/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit" data-branch="beaa86442a3902728118e6e5240b97da2fdca2ae" data-direction="back" data-pjax="true" itemscope="url" rel="nofollow"><span itemprop="title">rolit</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/niomaster/rolit/tree/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit/model" data-branch="beaa86442a3902728118e6e5240b97da2fdca2ae" data-direction="back" data-pjax="true" itemscope="url" rel="nofollow"><span itemprop="title">model</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/niomaster/rolit/tree/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit/model/networking" data-branch="beaa86442a3902728118e6e5240b97da2fdca2ae" data-direction="back" data-pjax="true" itemscope="url" rel="nofollow"><span itemprop="title">networking</span></a></span><span class="separator"> / </span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/niomaster/rolit/tree/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit/model/networking/server" data-branch="beaa86442a3902728118e6e5240b97da2fdca2ae" data-direction="back" data-pjax="true" itemscope="url" rel="nofollow"><span itemprop="title">server</span></a></span><span class="separator"> / </span><strong class="final-path">ServerProtocol.java</strong> <span class="js-zeroclipboard minibutton zeroclipboard-button" data-clipboard-text="src/rolit/model/networking/server/ServerProtocol.java" data-copied-hint="copied!" title="copy to clipboard"><span class="octicon octicon-clippy"></span></span>
  </div>
</div>


  <div class="commit file-history-tease">
    <img alt="Pieter" class="main-avatar" height="24" src="https://2.gravatar.com/avatar/30bed2d992d7e10854e6743f35e770ef?d=https%3A%2F%2Fidenticons.github.com%2Ffac9fa0a65c9b884d6a3b23850ed6733.png&amp;r=x&amp;s=140" width="24" />
    <span class="author"><a href="/niomaster" rel="author">niomaster</a></span>
    <time class="js-relative-date" datetime="2014-01-18T05:34:25-08:00" title="2014-01-18 05:34:25">January 18, 2014</time>
    <div class="commit-title">
        <a href="/niomaster/rolit/commit/beaa86442a3902728118e6e5240b97da2fdca2ae" class="message" data-pjax="true" title="Fix #1">Fix</a> <a href="https://github.com/niomaster/rolit/issues/1" class="issue-link" title="Het weggaan van spelers voordat een spel is gestart">#1</a>
    </div>

    <div class="participation">
      <p class="quickstat"><a href="#blob_contributors_box" rel="facebox"><strong>1</strong> contributor</a></p>
      
    </div>
    <div id="blob_contributors_box" style="display:none">
      <h2 class="facebox-header">Users who have contributed to this file</h2>
      <ul class="facebox-user-list">
          <li class="facebox-user-list-item">
            <img alt="Pieter" height="24" src="https://2.gravatar.com/avatar/30bed2d992d7e10854e6743f35e770ef?d=https%3A%2F%2Fidenticons.github.com%2Ffac9fa0a65c9b884d6a3b23850ed6733.png&amp;r=x&amp;s=140" width="24" />
            <a href="/niomaster">niomaster</a>
          </li>
      </ul>
    </div>
  </div>

<div id="files" class="bubble">
  <div class="file">
    <div class="meta">
      <div class="info">
        <span class="icon"><b class="octicon octicon-file-text"></b></span>
        <span class="mode" title="File Mode">file</span>
          <span>303 lines (262 sloc)</span>
        <span>11.828 kb</span>
      </div>
      <div class="actions">
        <div class="button-group">
              <a class="minibutton disabled tooltipped leftwards" href="#"
                 title="You must be on a branch to make or propose changes to this file">Edit</a>
          <a href="/niomaster/rolit/raw/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit/model/networking/server/ServerProtocol.java" class="button minibutton " id="raw-url">Raw</a>
            <a href="/niomaster/rolit/blame/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit/model/networking/server/ServerProtocol.java" class="button minibutton js-update-url-with-hash">Blame</a>
          <a href="/niomaster/rolit/commits/beaa86442a3902728118e6e5240b97da2fdca2ae/src/rolit/model/networking/server/ServerProtocol.java" class="button minibutton " rel="nofollow">History</a>
        </div><!-- /.button-group -->
          <a class="minibutton danger disabled empty-icon tooltipped leftwards" href="#"
             title="You must be on a branch to make or propose changes to this file">
          Delete
        </a>
      </div><!-- /.actions -->
    </div>
        <div class="blob-wrapper data type-java js-blob-data">
        <table class="file-code file-diff">
          <tr class="file-code-line">
            <td class="blob-line-nums">
              <span id="L1" rel="#L1">1</span>
<span id="L2" rel="#L2">2</span>
<span id="L3" rel="#L3">3</span>
<span id="L4" rel="#L4">4</span>
<span id="L5" rel="#L5">5</span>
<span id="L6" rel="#L6">6</span>
<span id="L7" rel="#L7">7</span>
<span id="L8" rel="#L8">8</span>
<span id="L9" rel="#L9">9</span>
<span id="L10" rel="#L10">10</span>
<span id="L11" rel="#L11">11</span>
<span id="L12" rel="#L12">12</span>
<span id="L13" rel="#L13">13</span>
<span id="L14" rel="#L14">14</span>
<span id="L15" rel="#L15">15</span>
<span id="L16" rel="#L16">16</span>
<span id="L17" rel="#L17">17</span>
<span id="L18" rel="#L18">18</span>
<span id="L19" rel="#L19">19</span>
<span id="L20" rel="#L20">20</span>
<span id="L21" rel="#L21">21</span>
<span id="L22" rel="#L22">22</span>
<span id="L23" rel="#L23">23</span>
<span id="L24" rel="#L24">24</span>
<span id="L25" rel="#L25">25</span>
<span id="L26" rel="#L26">26</span>
<span id="L27" rel="#L27">27</span>
<span id="L28" rel="#L28">28</span>
<span id="L29" rel="#L29">29</span>
<span id="L30" rel="#L30">30</span>
<span id="L31" rel="#L31">31</span>
<span id="L32" rel="#L32">32</span>
<span id="L33" rel="#L33">33</span>
<span id="L34" rel="#L34">34</span>
<span id="L35" rel="#L35">35</span>
<span id="L36" rel="#L36">36</span>
<span id="L37" rel="#L37">37</span>
<span id="L38" rel="#L38">38</span>
<span id="L39" rel="#L39">39</span>
<span id="L40" rel="#L40">40</span>
<span id="L41" rel="#L41">41</span>
<span id="L42" rel="#L42">42</span>
<span id="L43" rel="#L43">43</span>
<span id="L44" rel="#L44">44</span>
<span id="L45" rel="#L45">45</span>
<span id="L46" rel="#L46">46</span>
<span id="L47" rel="#L47">47</span>
<span id="L48" rel="#L48">48</span>
<span id="L49" rel="#L49">49</span>
<span id="L50" rel="#L50">50</span>
<span id="L51" rel="#L51">51</span>
<span id="L52" rel="#L52">52</span>
<span id="L53" rel="#L53">53</span>
<span id="L54" rel="#L54">54</span>
<span id="L55" rel="#L55">55</span>
<span id="L56" rel="#L56">56</span>
<span id="L57" rel="#L57">57</span>
<span id="L58" rel="#L58">58</span>
<span id="L59" rel="#L59">59</span>
<span id="L60" rel="#L60">60</span>
<span id="L61" rel="#L61">61</span>
<span id="L62" rel="#L62">62</span>
<span id="L63" rel="#L63">63</span>
<span id="L64" rel="#L64">64</span>
<span id="L65" rel="#L65">65</span>
<span id="L66" rel="#L66">66</span>
<span id="L67" rel="#L67">67</span>
<span id="L68" rel="#L68">68</span>
<span id="L69" rel="#L69">69</span>
<span id="L70" rel="#L70">70</span>
<span id="L71" rel="#L71">71</span>
<span id="L72" rel="#L72">72</span>
<span id="L73" rel="#L73">73</span>
<span id="L74" rel="#L74">74</span>
<span id="L75" rel="#L75">75</span>
<span id="L76" rel="#L76">76</span>
<span id="L77" rel="#L77">77</span>
<span id="L78" rel="#L78">78</span>
<span id="L79" rel="#L79">79</span>
<span id="L80" rel="#L80">80</span>
<span id="L81" rel="#L81">81</span>
<span id="L82" rel="#L82">82</span>
<span id="L83" rel="#L83">83</span>
<span id="L84" rel="#L84">84</span>
<span id="L85" rel="#L85">85</span>
<span id="L86" rel="#L86">86</span>
<span id="L87" rel="#L87">87</span>
<span id="L88" rel="#L88">88</span>
<span id="L89" rel="#L89">89</span>
<span id="L90" rel="#L90">90</span>
<span id="L91" rel="#L91">91</span>
<span id="L92" rel="#L92">92</span>
<span id="L93" rel="#L93">93</span>
<span id="L94" rel="#L94">94</span>
<span id="L95" rel="#L95">95</span>
<span id="L96" rel="#L96">96</span>
<span id="L97" rel="#L97">97</span>
<span id="L98" rel="#L98">98</span>
<span id="L99" rel="#L99">99</span>
<span id="L100" rel="#L100">100</span>
<span id="L101" rel="#L101">101</span>
<span id="L102" rel="#L102">102</span>
<span id="L103" rel="#L103">103</span>
<span id="L104" rel="#L104">104</span>
<span id="L105" rel="#L105">105</span>
<span id="L106" rel="#L106">106</span>
<span id="L107" rel="#L107">107</span>
<span id="L108" rel="#L108">108</span>
<span id="L109" rel="#L109">109</span>
<span id="L110" rel="#L110">110</span>
<span id="L111" rel="#L111">111</span>
<span id="L112" rel="#L112">112</span>
<span id="L113" rel="#L113">113</span>
<span id="L114" rel="#L114">114</span>
<span id="L115" rel="#L115">115</span>
<span id="L116" rel="#L116">116</span>
<span id="L117" rel="#L117">117</span>
<span id="L118" rel="#L118">118</span>
<span id="L119" rel="#L119">119</span>
<span id="L120" rel="#L120">120</span>
<span id="L121" rel="#L121">121</span>
<span id="L122" rel="#L122">122</span>
<span id="L123" rel="#L123">123</span>
<span id="L124" rel="#L124">124</span>
<span id="L125" rel="#L125">125</span>
<span id="L126" rel="#L126">126</span>
<span id="L127" rel="#L127">127</span>
<span id="L128" rel="#L128">128</span>
<span id="L129" rel="#L129">129</span>
<span id="L130" rel="#L130">130</span>
<span id="L131" rel="#L131">131</span>
<span id="L132" rel="#L132">132</span>
<span id="L133" rel="#L133">133</span>
<span id="L134" rel="#L134">134</span>
<span id="L135" rel="#L135">135</span>
<span id="L136" rel="#L136">136</span>
<span id="L137" rel="#L137">137</span>
<span id="L138" rel="#L138">138</span>
<span id="L139" rel="#L139">139</span>
<span id="L140" rel="#L140">140</span>
<span id="L141" rel="#L141">141</span>
<span id="L142" rel="#L142">142</span>
<span id="L143" rel="#L143">143</span>
<span id="L144" rel="#L144">144</span>
<span id="L145" rel="#L145">145</span>
<span id="L146" rel="#L146">146</span>
<span id="L147" rel="#L147">147</span>
<span id="L148" rel="#L148">148</span>
<span id="L149" rel="#L149">149</span>
<span id="L150" rel="#L150">150</span>
<span id="L151" rel="#L151">151</span>
<span id="L152" rel="#L152">152</span>
<span id="L153" rel="#L153">153</span>
<span id="L154" rel="#L154">154</span>
<span id="L155" rel="#L155">155</span>
<span id="L156" rel="#L156">156</span>
<span id="L157" rel="#L157">157</span>
<span id="L158" rel="#L158">158</span>
<span id="L159" rel="#L159">159</span>
<span id="L160" rel="#L160">160</span>
<span id="L161" rel="#L161">161</span>
<span id="L162" rel="#L162">162</span>
<span id="L163" rel="#L163">163</span>
<span id="L164" rel="#L164">164</span>
<span id="L165" rel="#L165">165</span>
<span id="L166" rel="#L166">166</span>
<span id="L167" rel="#L167">167</span>
<span id="L168" rel="#L168">168</span>
<span id="L169" rel="#L169">169</span>
<span id="L170" rel="#L170">170</span>
<span id="L171" rel="#L171">171</span>
<span id="L172" rel="#L172">172</span>
<span id="L173" rel="#L173">173</span>
<span id="L174" rel="#L174">174</span>
<span id="L175" rel="#L175">175</span>
<span id="L176" rel="#L176">176</span>
<span id="L177" rel="#L177">177</span>
<span id="L178" rel="#L178">178</span>
<span id="L179" rel="#L179">179</span>
<span id="L180" rel="#L180">180</span>
<span id="L181" rel="#L181">181</span>
<span id="L182" rel="#L182">182</span>
<span id="L183" rel="#L183">183</span>
<span id="L184" rel="#L184">184</span>
<span id="L185" rel="#L185">185</span>
<span id="L186" rel="#L186">186</span>
<span id="L187" rel="#L187">187</span>
<span id="L188" rel="#L188">188</span>
<span id="L189" rel="#L189">189</span>
<span id="L190" rel="#L190">190</span>
<span id="L191" rel="#L191">191</span>
<span id="L192" rel="#L192">192</span>
<span id="L193" rel="#L193">193</span>
<span id="L194" rel="#L194">194</span>
<span id="L195" rel="#L195">195</span>
<span id="L196" rel="#L196">196</span>
<span id="L197" rel="#L197">197</span>
<span id="L198" rel="#L198">198</span>
<span id="L199" rel="#L199">199</span>
<span id="L200" rel="#L200">200</span>
<span id="L201" rel="#L201">201</span>
<span id="L202" rel="#L202">202</span>
<span id="L203" rel="#L203">203</span>
<span id="L204" rel="#L204">204</span>
<span id="L205" rel="#L205">205</span>
<span id="L206" rel="#L206">206</span>
<span id="L207" rel="#L207">207</span>
<span id="L208" rel="#L208">208</span>
<span id="L209" rel="#L209">209</span>
<span id="L210" rel="#L210">210</span>
<span id="L211" rel="#L211">211</span>
<span id="L212" rel="#L212">212</span>
<span id="L213" rel="#L213">213</span>
<span id="L214" rel="#L214">214</span>
<span id="L215" rel="#L215">215</span>
<span id="L216" rel="#L216">216</span>
<span id="L217" rel="#L217">217</span>
<span id="L218" rel="#L218">218</span>
<span id="L219" rel="#L219">219</span>
<span id="L220" rel="#L220">220</span>
<span id="L221" rel="#L221">221</span>
<span id="L222" rel="#L222">222</span>
<span id="L223" rel="#L223">223</span>
<span id="L224" rel="#L224">224</span>
<span id="L225" rel="#L225">225</span>
<span id="L226" rel="#L226">226</span>
<span id="L227" rel="#L227">227</span>
<span id="L228" rel="#L228">228</span>
<span id="L229" rel="#L229">229</span>
<span id="L230" rel="#L230">230</span>
<span id="L231" rel="#L231">231</span>
<span id="L232" rel="#L232">232</span>
<span id="L233" rel="#L233">233</span>
<span id="L234" rel="#L234">234</span>
<span id="L235" rel="#L235">235</span>
<span id="L236" rel="#L236">236</span>
<span id="L237" rel="#L237">237</span>
<span id="L238" rel="#L238">238</span>
<span id="L239" rel="#L239">239</span>
<span id="L240" rel="#L240">240</span>
<span id="L241" rel="#L241">241</span>
<span id="L242" rel="#L242">242</span>
<span id="L243" rel="#L243">243</span>
<span id="L244" rel="#L244">244</span>
<span id="L245" rel="#L245">245</span>
<span id="L246" rel="#L246">246</span>
<span id="L247" rel="#L247">247</span>
<span id="L248" rel="#L248">248</span>
<span id="L249" rel="#L249">249</span>
<span id="L250" rel="#L250">250</span>
<span id="L251" rel="#L251">251</span>
<span id="L252" rel="#L252">252</span>
<span id="L253" rel="#L253">253</span>
<span id="L254" rel="#L254">254</span>
<span id="L255" rel="#L255">255</span>
<span id="L256" rel="#L256">256</span>
<span id="L257" rel="#L257">257</span>
<span id="L258" rel="#L258">258</span>
<span id="L259" rel="#L259">259</span>
<span id="L260" rel="#L260">260</span>
<span id="L261" rel="#L261">261</span>
<span id="L262" rel="#L262">262</span>
<span id="L263" rel="#L263">263</span>
<span id="L264" rel="#L264">264</span>
<span id="L265" rel="#L265">265</span>
<span id="L266" rel="#L266">266</span>
<span id="L267" rel="#L267">267</span>
<span id="L268" rel="#L268">268</span>
<span id="L269" rel="#L269">269</span>
<span id="L270" rel="#L270">270</span>
<span id="L271" rel="#L271">271</span>
<span id="L272" rel="#L272">272</span>
<span id="L273" rel="#L273">273</span>
<span id="L274" rel="#L274">274</span>
<span id="L275" rel="#L275">275</span>
<span id="L276" rel="#L276">276</span>
<span id="L277" rel="#L277">277</span>
<span id="L278" rel="#L278">278</span>
<span id="L279" rel="#L279">279</span>
<span id="L280" rel="#L280">280</span>
<span id="L281" rel="#L281">281</span>
<span id="L282" rel="#L282">282</span>
<span id="L283" rel="#L283">283</span>
<span id="L284" rel="#L284">284</span>
<span id="L285" rel="#L285">285</span>
<span id="L286" rel="#L286">286</span>
<span id="L287" rel="#L287">287</span>
<span id="L288" rel="#L288">288</span>
<span id="L289" rel="#L289">289</span>
<span id="L290" rel="#L290">290</span>
<span id="L291" rel="#L291">291</span>
<span id="L292" rel="#L292">292</span>
<span id="L293" rel="#L293">293</span>
<span id="L294" rel="#L294">294</span>
<span id="L295" rel="#L295">295</span>
<span id="L296" rel="#L296">296</span>
<span id="L297" rel="#L297">297</span>
<span id="L298" rel="#L298">298</span>
<span id="L299" rel="#L299">299</span>
<span id="L300" rel="#L300">300</span>
<span id="L301" rel="#L301">301</span>
<span id="L302" rel="#L302">302</span>

            </td>
            <td class="blob-line-code">
                    <div class="code-body highlight"><pre><div class='line' id='LC1'><span class="kn">package</span> <span class="n">rolit</span><span class="o">.</span><span class="na">model</span><span class="o">.</span><span class="na">networking</span><span class="o">.</span><span class="na">server</span><span class="o">;</span></div><div class='line' id='LC2'><br/></div><div class='line' id='LC3'><span class="kn">import</span> <span class="nn">rolit.model.networking.common.CommonProtocol</span><span class="o">;</span></div><div class='line' id='LC4'><br/></div><div class='line' id='LC5'><span class="kn">import</span> <span class="nn">java.io.IOException</span><span class="o">;</span></div><div class='line' id='LC6'><br/></div><div class='line' id='LC7'><span class="cm">/**</span></div><div class='line' id='LC8'><span class="cm"> * @author Pieter Bos</span></div><div class='line' id='LC9'><span class="cm"> * @author Martijn de Bijl</span></div><div class='line' id='LC10'><span class="cm"> *</span></div><div class='line' id='LC11'><span class="cm"> * Abstract class met alle constanten en methodes die gebruikt kunnen worden</span></div><div class='line' id='LC12'><span class="cm"> */</span></div><div class='line' id='LC13'><span class="kd">public</span> <span class="kd">abstract</span> <span class="kd">class</span> <span class="nc">ServerProtocol</span> <span class="kd">extends</span> <span class="n">CommonProtocol</span> <span class="o">{</span></div><div class='line' id='LC14'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC15'><span class="cm">     * Constante voor het handshake-commando</span></div><div class='line' id='LC16'><span class="cm">     */</span></div><div class='line' id='LC17'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">HANDSHAKE</span> <span class="o">=</span> <span class="s">&quot;hello&quot;</span><span class="o">;</span></div><div class='line' id='LC18'><br/></div><div class='line' id='LC19'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC20'><span class="cm">     * Constante voor het authOk-commando</span></div><div class='line' id='LC21'><span class="cm">     */</span></div><div class='line' id='LC22'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">AUTH_OK</span> <span class="o">=</span> <span class="s">&quot;authOk&quot;</span><span class="o">;</span></div><div class='line' id='LC23'><br/></div><div class='line' id='LC24'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC25'><span class="cm">     * Constante voor het error-commando</span></div><div class='line' id='LC26'><span class="cm">     */</span></div><div class='line' id='LC27'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">ERROR</span> <span class="o">=</span> <span class="s">&quot;error&quot;</span><span class="o">;</span></div><div class='line' id='LC28'><br/></div><div class='line' id='LC29'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC30'><span class="cm">     * Constante voor het game-commando</span></div><div class='line' id='LC31'><span class="cm">     */</span></div><div class='line' id='LC32'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">GAME</span> <span class="o">=</span> <span class="s">&quot;game&quot;</span><span class="o">;</span></div><div class='line' id='LC33'><br/></div><div class='line' id='LC34'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC35'><span class="cm">     * Constante voor het start-commando</span></div><div class='line' id='LC36'><span class="cm">     */</span></div><div class='line' id='LC37'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">START</span> <span class="o">=</span> <span class="s">&quot;start&quot;</span><span class="o">;</span></div><div class='line' id='LC38'><br/></div><div class='line' id='LC39'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC40'><span class="cm">     * Constante voor het move-commando</span></div><div class='line' id='LC41'><span class="cm">     */</span></div><div class='line' id='LC42'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">MOVE</span> <span class="o">=</span> <span class="s">&quot;move&quot;</span><span class="o">;</span></div><div class='line' id='LC43'><br/></div><div class='line' id='LC44'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC45'><span class="cm">     * Constante voor het move-done-commando</span></div><div class='line' id='LC46'><span class="cm">     */</span></div><div class='line' id='LC47'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">MOVE_DONE</span> <span class="o">=</span> <span class="s">&quot;moveDone&quot;</span><span class="o">;</span></div><div class='line' id='LC48'><br/></div><div class='line' id='LC49'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC50'><span class="cm">     * Constante voor het game-over-command</span></div><div class='line' id='LC51'><span class="cm">     */</span></div><div class='line' id='LC52'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">GAME_OVER</span> <span class="o">=</span> <span class="s">&quot;gameOver&quot;</span><span class="o">;</span></div><div class='line' id='LC53'><br/></div><div class='line' id='LC54'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC55'><span class="cm">     * Constante voor het message-commando</span></div><div class='line' id='LC56'><span class="cm">     */</span></div><div class='line' id='LC57'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">MESSAGE</span> <span class="o">=</span> <span class="s">&quot;message&quot;</span><span class="o">;</span></div><div class='line' id='LC58'><br/></div><div class='line' id='LC59'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC60'><span class="cm">     * Constante voor het challenge-commando</span></div><div class='line' id='LC61'><span class="cm">     */</span></div><div class='line' id='LC62'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">CHALLENGE</span> <span class="o">=</span> <span class="s">&quot;challenge&quot;</span><span class="o">;</span></div><div class='line' id='LC63'><br/></div><div class='line' id='LC64'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC65'><span class="cm">     * Constante voor het challenge-response-commando</span></div><div class='line' id='LC66'><span class="cm">     */</span></div><div class='line' id='LC67'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">CHALLENGE_RESPONSE</span> <span class="o">=</span> <span class="s">&quot;challengeResponse&quot;</span><span class="o">;</span></div><div class='line' id='LC68'><br/></div><div class='line' id='LC69'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC70'><span class="cm">     * Constante voor het can-be-challenged-commando</span></div><div class='line' id='LC71'><span class="cm">     */</span></div><div class='line' id='LC72'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">CAN_BE_CHALLENGED</span> <span class="o">=</span> <span class="s">&quot;canBeChallenged&quot;</span><span class="o">;</span></div><div class='line' id='LC73'><br/></div><div class='line' id='LC74'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC75'><span class="cm">     *</span></div><div class='line' id='LC76'><span class="cm">     */</span></div><div class='line' id='LC77'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">ONLINE</span> <span class="o">=</span> <span class="s">&quot;online&quot;</span><span class="o">;</span></div><div class='line' id='LC78'><br/></div><div class='line' id='LC79'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC80'><span class="cm">     * Constante voor highscore</span></div><div class='line' id='LC81'><span class="cm">     */</span></div><div class='line' id='LC82'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="n">String</span> <span class="n">HIGHSCORE</span> <span class="o">=</span> <span class="s">&quot;highscore&quot;</span><span class="o">;</span></div><div class='line' id='LC83'><br/></div><div class='line' id='LC84'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_GENERIC</span> <span class="o">=</span> <span class="o">-</span><span class="mi">1</span><span class="o">;</span></div><div class='line' id='LC85'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_INVALID_LOGIN</span> <span class="o">=</span> <span class="mi">1</span><span class="o">;</span></div><div class='line' id='LC86'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_GAME_FULL</span> <span class="o">=</span> <span class="mi">2</span><span class="o">;</span></div><div class='line' id='LC87'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_TOO_LITTLE_PLAYERS</span> <span class="o">=</span> <span class="mi">3</span><span class="o">;</span></div><div class='line' id='LC88'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_INVALID_MOVE</span> <span class="o">=</span> <span class="mi">4</span><span class="o">;</span></div><div class='line' id='LC89'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_NO_SUCH_GAME</span> <span class="o">=</span> <span class="mi">5</span><span class="o">;</span></div><div class='line' id='LC90'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_USER_HAS_NO_GAME</span> <span class="o">=</span> <span class="mi">6</span><span class="o">;</span></div><div class='line' id='LC91'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_HANDSHAKE_MISSING</span> <span class="o">=</span> <span class="mi">7</span><span class="o">;</span></div><div class='line' id='LC92'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">ERROR_USER_ALREADY_HAS_GAME</span> <span class="o">=</span> <span class="mi">8</span><span class="o">;</span></div><div class='line' id='LC93'><br/></div><div class='line' id='LC94'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">HIGHSCORE_UNAVAILABLE</span> <span class="o">=</span> <span class="o">-</span><span class="mi">1</span><span class="o">;</span></div><div class='line' id='LC95'><br/></div><div class='line' id='LC96'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC97'><span class="cm">     * Status om aan te geven dat de creator voortijdig is weggegaan</span></div><div class='line' id='LC98'><span class="cm">     */</span></div><div class='line' id='LC99'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">STATUS_PREMATURE_LEAVE</span> <span class="o">=</span> <span class="o">-</span><span class="mi">1</span><span class="o">;</span></div><div class='line' id='LC100'><br/></div><div class='line' id='LC101'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC102'><span class="cm">     * Status om aan te geven dat het spel nog niet is gestart.</span></div><div class='line' id='LC103'><span class="cm">     */</span></div><div class='line' id='LC104'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">STATUS_NOT_STARTED</span> <span class="o">=</span> <span class="mi">0</span><span class="o">;</span></div><div class='line' id='LC105'><br/></div><div class='line' id='LC106'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC107'><span class="cm">     * Status om aan te geven dat het spel is gestart.</span></div><div class='line' id='LC108'><span class="cm">     */</span></div><div class='line' id='LC109'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">static</span> <span class="kd">final</span> <span class="kt">int</span> <span class="n">STATUS_STARTED</span> <span class="o">=</span> <span class="mi">1</span><span class="o">;</span></div><div class='line' id='LC110'><br/></div><div class='line' id='LC111'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC112'><span class="cm">     * Antwoord op de handshake van de client. Moet altijd het eerst verzonden commando zijn, met uitzondering van</span></div><div class='line' id='LC113'><span class="cm">     * errors.</span></div><div class='line' id='LC114'><span class="cm">     * @requires Dat de handshake van de client is verzonden.</span></div><div class='line' id='LC115'><span class="cm">     * @requires Dat de handshake van de client niet een al ingelodge naam kiest.</span></div><div class='line' id='LC116'><span class="cm">     * @requires Dat de clientName niet begint met &quot;player_&quot;</span></div><div class='line' id='LC117'><span class="cm">     * @param supports Wat de server ondersteunt.</span></div><div class='line' id='LC118'><span class="cm">     * @param version Een beschrijving van wat de server kan</span></div><div class='line' id='LC119'><span class="cm">     */</span></div><div class='line' id='LC120'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">handshake</span><span class="o">(</span><span class="kt">int</span> <span class="n">supports</span><span class="o">,</span> <span class="n">String</span> <span class="n">version</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC121'><br/></div><div class='line' id='LC122'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC123'><span class="cm">     * Antwoord op de handshake van de client. Moet altijd het eerst verzonden commando zijn, met uitzondering van</span></div><div class='line' id='LC124'><span class="cm">     * errors.</span></div><div class='line' id='LC125'><span class="cm">     * @requires Dat de requirements van de eerste overload zijn voldaan.</span></div><div class='line' id='LC126'><span class="cm">     * @requires Dat de clientName juist wel begint met &quot;player_&quot;</span></div><div class='line' id='LC127'><span class="cm">     * @param supports</span></div><div class='line' id='LC128'><span class="cm">     * @param version</span></div><div class='line' id='LC129'><span class="cm">     * @param nonce</span></div><div class='line' id='LC130'><span class="cm">     * @throws IOException</span></div><div class='line' id='LC131'><span class="cm">     */</span></div><div class='line' id='LC132'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">handshake</span><span class="o">(</span><span class="kt">int</span> <span class="n">supports</span><span class="o">,</span> <span class="n">String</span> <span class="n">version</span><span class="o">,</span> <span class="n">String</span> <span class="n">nonce</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC133'><br/></div><div class='line' id='LC134'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC135'><span class="cm">     * Antwoord op het auth-pakket van de client.</span></div><div class='line' id='LC136'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC137'><span class="cm">     * @requires Dat de client een juist gesignde nonce heeft gestuurd.</span></div><div class='line' id='LC138'><span class="cm">     * @requires Dat de nonce gesigned is met de juiste public-key volgens ss-security.student.utwente.nl</span></div><div class='line' id='LC139'><span class="cm">     * @throws IOException</span></div><div class='line' id='LC140'><span class="cm">     */</span></div><div class='line' id='LC141'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">authOk</span><span class="o">()</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC142'><br/></div><div class='line' id='LC143'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC144'><span class="cm">     * Commando om de client te laten weten dat hij iets fout heeft gedaan, waardoor de verbinding moet worden</span></div><div class='line' id='LC145'><span class="cm">     * verbroken.</span></div><div class='line' id='LC146'><span class="cm">     * @requires Dat de client iets fout heeft gedaan...</span></div><div class='line' id='LC147'><span class="cm">     * @requires Dat dit het enige en eerste pakket na de fout is.</span></div><div class='line' id='LC148'><span class="cm">     * @param errorCode De error-code, op te zoeken in de errorCode-tabel.</span></div><div class='line' id='LC149'><span class="cm">     */</span></div><div class='line' id='LC150'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">error</span><span class="o">(</span><span class="kt">int</span> <span class="n">errorCode</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC151'><br/></div><div class='line' id='LC152'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC153'><span class="cm">     * Commando om de client te laten weten dat er óf een nieuw spel is, óf dat er een spel is veranderd in status.</span></div><div class='line' id='LC154'><span class="cm">     * Clients krijgen een serie van deze commando&#39;s na de handshake om zo een lijst van alle spellen op te bouwen. Als</span></div><div class='line' id='LC155'><span class="cm">     * er daarna iets verandert aan het aantal spelers of dat het spel is begonnen moet de server weer een update</span></div><div class='line' id='LC156'><span class="cm">     * sturen.</span></div><div class='line' id='LC157'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC158'><span class="cm">     * @requires Dat óf het spel in hasStarted-status is veranderd, óf in aantal spelers is veranderd, óf dat de client</span></div><div class='line' id='LC159'><span class="cm">     * nog niet de volledige lijst met spellen heeft ontvangen direct na de handshake.</span></div><div class='line' id='LC160'><span class="cm">     * @param creator De maker van het spel.</span></div><div class='line' id='LC161'><span class="cm">     * @param hasStarted De status van het spel.</span></div><div class='line' id='LC162'><span class="cm">     * @param noPlayers Het aantal spelers in het spel.</span></div><div class='line' id='LC163'><span class="cm">     */</span></div><div class='line' id='LC164'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">game</span><span class="o">(</span><span class="n">String</span> <span class="n">creator</span><span class="o">,</span> <span class="kt">int</span> <span class="n">status</span><span class="o">,</span> <span class="kt">int</span> <span class="n">noPlayers</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC165'><br/></div><div class='line' id='LC166'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC167'><span class="cm">     * Commando om een spel te starten met twee spelers, die in die volgorde een zet moeten doen.</span></div><div class='line' id='LC168'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC169'><span class="cm">     * @requires Dat de speler in een spel zit.</span></div><div class='line' id='LC170'><span class="cm">     * @requires Dat de creator van het spel het spel heeft gestart.</span></div><div class='line' id='LC171'><span class="cm">     * @requires Dat dit bericht nog niet is gestuurd voor dit spel.</span></div><div class='line' id='LC172'><span class="cm">     * @param playerOne De eerste speler</span></div><div class='line' id='LC173'><span class="cm">     * @param playerTwo De tweede speler</span></div><div class='line' id='LC174'><span class="cm">     */</span></div><div class='line' id='LC175'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">start</span><span class="o">(</span><span class="n">String</span> <span class="n">playerOne</span><span class="o">,</span> <span class="n">String</span> <span class="n">playerTwo</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC176'><br/></div><div class='line' id='LC177'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC178'><span class="cm">     * Commando om een spel te starten met drie spelers, die in die volgorde een zet moeten doen.</span></div><div class='line' id='LC179'><span class="cm">     * @requires Dat de requirements bij de eerste overload zijn voldaan.</span></div><div class='line' id='LC180'><span class="cm">     * @param playerOne De eerste speler</span></div><div class='line' id='LC181'><span class="cm">     * @param playerTwo De tweede speler</span></div><div class='line' id='LC182'><span class="cm">     * @param playerThree De derde speler</span></div><div class='line' id='LC183'><span class="cm">     */</span></div><div class='line' id='LC184'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">start</span><span class="o">(</span><span class="n">String</span> <span class="n">playerOne</span><span class="o">,</span> <span class="n">String</span> <span class="n">playerTwo</span><span class="o">,</span> <span class="n">String</span> <span class="n">playerThree</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC185'><br/></div><div class='line' id='LC186'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC187'><span class="cm">     * Commando om een spel te starten met vier spelers, die in die volgorde een zet moeten doen.</span></div><div class='line' id='LC188'><span class="cm">     * @requires Dat de requirements bij de eerste overload zijn voldaan.</span></div><div class='line' id='LC189'><span class="cm">     * @param playerOne De eerste speler</span></div><div class='line' id='LC190'><span class="cm">     * @param playerTwo De tweede speler</span></div><div class='line' id='LC191'><span class="cm">     * @param playerThree De derde speler</span></div><div class='line' id='LC192'><span class="cm">     * @param playerFour De vierde speler</span></div><div class='line' id='LC193'><span class="cm">     */</span></div><div class='line' id='LC194'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">start</span><span class="o">(</span><span class="n">String</span> <span class="n">playerOne</span><span class="o">,</span> <span class="n">String</span> <span class="n">playerTwo</span><span class="o">,</span> <span class="n">String</span> <span class="n">playerThree</span><span class="o">,</span> <span class="n">String</span> <span class="n">playerFour</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC195'><br/></div><div class='line' id='LC196'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC197'><span class="cm">     * Commando om de client te vertellen dat hij een zet moet gaan doen.</span></div><div class='line' id='LC198'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC199'><span class="cm">     * @requires Dat de speler in een spel zit.</span></div><div class='line' id='LC200'><span class="cm">     * @requires Dat het spel is gestart.</span></div><div class='line' id='LC201'><span class="cm">     * @requires Dat de speler ook echt aan de beurt is.</span></div><div class='line' id='LC202'><span class="cm">     */</span></div><div class='line' id='LC203'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">move</span><span class="o">()</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC204'><br/></div><div class='line' id='LC205'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC206'><span class="cm">     * Commando om de client te laten weten dat iemand een zet heeft gedaan in het huidige spel.</span></div><div class='line' id='LC207'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC208'><span class="cm">     * @requires Dat de speler in een spel zit.</span></div><div class='line' id='LC209'><span class="cm">     * @requires Dat het spel is gestart.</span></div><div class='line' id='LC210'><span class="cm">     * @requires Dat de speler die zet heeft gedaan</span></div><div class='line' id='LC211'><span class="cm">     * @param name Naam van de speler die de zet heeft gedan.</span></div><div class='line' id='LC212'><span class="cm">     * @param x X-coördinaat, waarbij de linkerkant 0 is en de rechterkant 7.</span></div><div class='line' id='LC213'><span class="cm">     * @param y Y-coördinaat, waarbij de bovenkant 0 is en de onderkant 7.</span></div><div class='line' id='LC214'><span class="cm">     */</span></div><div class='line' id='LC215'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">moveDone</span><span class="o">(</span><span class="n">String</span> <span class="n">name</span><span class="o">,</span> <span class="kt">int</span> <span class="n">x</span><span class="o">,</span> <span class="kt">int</span> <span class="n">y</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC216'><br/></div><div class='line' id='LC217'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC218'><span class="cm">     * Commando om de client te laten weten dat het spel is afgelopen, om welke reden dan ook. Eventueel zijn er</span></div><div class='line' id='LC219'><span class="cm">     * winnaars als het spel helemaal is voltooid. De server mag bepalen wat er gebeurt als er meerdere mensen dezelfde</span></div><div class='line' id='LC220'><span class="cm">     * score hebben.</span></div><div class='line' id='LC221'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC222'><span class="cm">     * @requires Dat de speler in een spel zit.</span></div><div class='line' id='LC223'><span class="cm">     * @requires Dat het spel is gestart.</span></div><div class='line' id='LC224'><span class="cm">     * @requires Dat:</span></div><div class='line' id='LC225'><span class="cm">     *           * Als het spel is gestart: ofwel het spel is afgelopen volgens de regels van de server ofwel één van de</span></div><div class='line' id='LC226'><span class="cm">     *                 mensen is weggegaan</span></div><div class='line' id='LC227'><span class="cm">     * @param score De hoogste score</span></div><div class='line' id='LC228'><span class="cm">     * @param winners De mensen met die score</span></div><div class='line' id='LC229'><span class="cm">     */</span></div><div class='line' id='LC230'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">gameOver</span><span class="o">(</span><span class="kt">int</span> <span class="n">score</span><span class="o">,</span> <span class="n">String</span><span class="o">[]</span> <span class="n">winners</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC231'><br/></div><div class='line' id='LC232'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC233'><span class="cm">     * Commando om de client op te hoogte te stellen van een chatbericht</span></div><div class='line' id='LC234'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC235'><span class="cm">     * @requires Dat de speler dit bericht heeft verzonden.</span></div><div class='line' id='LC236'><span class="cm">     * @param name Afzender van het chatbericht</span></div><div class='line' id='LC237'><span class="cm">     * @param body Tekst van het chatbericht</span></div><div class='line' id='LC238'><span class="cm">     */</span></div><div class='line' id='LC239'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">message</span><span class="o">(</span><span class="n">String</span> <span class="n">name</span><span class="o">,</span> <span class="n">String</span> <span class="n">body</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC240'><br/></div><div class='line' id='LC241'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC242'><span class="cm">     * Commando om de client op te hoogte te stellen van een uitdaging met twee mensen.</span></div><div class='line' id='LC243'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC244'><span class="cm">     * @requires Dat de speler niet is uitgedaagd.</span></div><div class='line' id='LC245'><span class="cm">     * @requires Dat de speler niet in een spel zit.</span></div><div class='line' id='LC246'><span class="cm">     * @requires Dat de speler uitdaagbaar is.</span></div><div class='line' id='LC247'><span class="cm">     * @param challenger De uitdager</span></div><div class='line' id='LC248'><span class="cm">     */</span></div><div class='line' id='LC249'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">challenge</span><span class="o">(</span><span class="n">String</span> <span class="n">challenger</span><span class="o">,</span> <span class="n">String</span> <span class="n">other1</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC250'><br/></div><div class='line' id='LC251'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC252'><span class="cm">     * Commando om de client op de hoogte te stellen van een uitdaging met drie mensen</span></div><div class='line' id='LC253'><span class="cm">     * @requires Dat de requirements bij de eerste overload zijn voldaan.</span></div><div class='line' id='LC254'><span class="cm">     * @param challenger De uitdager</span></div><div class='line' id='LC255'><span class="cm">     * @param other1 Andere gebruiker</span></div><div class='line' id='LC256'><span class="cm">     */</span></div><div class='line' id='LC257'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">challenge</span><span class="o">(</span><span class="n">String</span> <span class="n">challenger</span><span class="o">,</span> <span class="n">String</span> <span class="n">other1</span><span class="o">,</span> <span class="n">String</span> <span class="n">other2</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC258'><br/></div><div class='line' id='LC259'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC260'><span class="cm">     * Commando om de client op de hoogte te stellen van een uitdaging met vier mensen</span></div><div class='line' id='LC261'><span class="cm">     * @requires Dat de requirements bij de eerste overload zijn voldaan.</span></div><div class='line' id='LC262'><span class="cm">     * @param challenger De uitdager</span></div><div class='line' id='LC263'><span class="cm">     * @param other1 Andere gebruiker 1</span></div><div class='line' id='LC264'><span class="cm">     * @param other2 Andere gebruiker 2</span></div><div class='line' id='LC265'><span class="cm">     */</span></div><div class='line' id='LC266'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">challenge</span><span class="o">(</span><span class="n">String</span> <span class="n">challenger</span><span class="o">,</span> <span class="n">String</span> <span class="n">other1</span><span class="o">,</span> <span class="n">String</span> <span class="n">other2</span><span class="o">,</span> <span class="n">String</span> <span class="n">other3</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC267'><br/></div><div class='line' id='LC268'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC269'><span class="cm">     * Commando om mensen die in een uitdaging zitten op de hoogte te stellen van de status van de uitgedaagden.</span></div><div class='line' id='LC270'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC271'><span class="cm">     * @requires Dat de client is uitgedaagd.</span></div><div class='line' id='LC272'><span class="cm">     * @param name Naam van de uitgedaagde.</span></div><div class='line' id='LC273'><span class="cm">     * @param accept Of deze persoon accepteert.</span></div><div class='line' id='LC274'><span class="cm">     */</span></div><div class='line' id='LC275'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">challengeResponse</span><span class="o">(</span><span class="n">String</span> <span class="n">name</span><span class="o">,</span> <span class="kt">boolean</span> <span class="n">accept</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC276'><br/></div><div class='line' id='LC277'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC278'><span class="cm">     * Commando om de client op de hoogte te stellen van het veranderen van de status van iemand.</span></div><div class='line' id='LC279'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC280'><span class="cm">     * @requires Dat de flag true is als de speler is uitgedaagd of een uitdager is.</span></div><div class='line' id='LC281'><span class="cm">     * @param name Naam van de uitgedaagde</span></div><div class='line' id='LC282'><span class="cm">     * @param flag Of hij kan worden uitgedaagd.</span></div><div class='line' id='LC283'><span class="cm">     */</span></div><div class='line' id='LC284'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">canBeChallenged</span><span class="o">(</span><span class="n">String</span> <span class="n">name</span><span class="o">,</span> <span class="kt">boolean</span> <span class="n">flag</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC285'><br/></div><div class='line' id='LC286'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC287'><span class="cm">     * Commando om de client op de hoogte te stellen van de gevraagde highscores.</span></div><div class='line' id='LC288'><span class="cm">     * @requires Dat de handshake is gedaan.</span></div><div class='line' id='LC289'><span class="cm">     * @requires Dat de client heeft gevraagd om highscores.</span></div><div class='line' id='LC290'><span class="cm">     * @param args Argumenten</span></div><div class='line' id='LC291'><span class="cm">     */</span></div><div class='line' id='LC292'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">highscore</span><span class="o">(</span><span class="n">String</span><span class="o">[]</span> <span class="n">args</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC293'><br/></div><div class='line' id='LC294'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="cm">/**</span></div><div class='line' id='LC295'><span class="cm">     * Commando om de client op de hoogte te stellen van een gebruiker die inlogt of weggaat</span></div><div class='line' id='LC296'><span class="cm">     * @requires Dat de handshake is gedaan</span></div><div class='line' id='LC297'><span class="cm">     * @requires Dat ófwel er een client bijkomt, ófwel er een client weggaat, ófwel dat de client de lijst met mensen</span></div><div class='line' id='LC298'><span class="cm">     * nog niet in zijn geheel heeft ontvangen na de lijst van spellen. Dit laatste is geen requirement voor servers</span></div><div class='line' id='LC299'><span class="cm">     * zonder chat.</span></div><div class='line' id='LC300'><span class="cm">     */</span></div><div class='line' id='LC301'>&nbsp;&nbsp;&nbsp;&nbsp;<span class="kd">public</span> <span class="kd">abstract</span> <span class="kt">void</span> <span class="nf">online</span><span class="o">(</span><span class="n">String</span> <span class="n">name</span><span class="o">,</span> <span class="kt">boolean</span> <span class="n">isOnline</span><span class="o">)</span> <span class="kd">throws</span> <span class="n">IOException</span><span class="o">;</span></div><div class='line' id='LC302'><span class="o">}</span></div></pre></div>
            </td>
          </tr>
        </table>
  </div>

  </div>
</div>

<a href="#jump-to-line" rel="facebox[.linejump]" data-hotkey="l" class="js-jump-to-line" style="display:none">Jump to Line</a>
<div id="jump-to-line" style="display:none">
  <form accept-charset="UTF-8" class="js-jump-to-line-form">
    <input class="linejump-input js-jump-to-line-field" type="text" placeholder="Jump to line&hellip;" autofocus>
    <button type="submit" class="button">Go</button>
  </form>
</div>

        </div>

      </div><!-- /.repo-container -->
      <div class="modal-backdrop"></div>
    </div><!-- /.container -->
  </div><!-- /.site -->


    </div><!-- /.wrapper -->

      <div class="container">
  <div class="site-footer">
    <ul class="site-footer-links right">
      <li><a href="https://status.github.com/">Status</a></li>
      <li><a href="http://developer.github.com">API</a></li>
      <li><a href="http://training.github.com">Training</a></li>
      <li><a href="http://shop.github.com">Shop</a></li>
      <li><a href="/blog">Blog</a></li>
      <li><a href="/about">About</a></li>

    </ul>

    <a href="/">
      <span class="mega-octicon octicon-mark-github" title="GitHub"></span>
    </a>

    <ul class="site-footer-links">
      <li>&copy; 2014 <span title="0.03711s from github-fe120-cp1-prd.iad.github.net">GitHub</span>, Inc.</li>
        <li><a href="/site/terms">Terms</a></li>
        <li><a href="/site/privacy">Privacy</a></li>
        <li><a href="/security">Security</a></li>
        <li><a href="/contact">Contact</a></li>
    </ul>
  </div><!-- /.site-footer -->
</div><!-- /.container -->


    <div class="fullscreen-overlay js-fullscreen-overlay" id="fullscreen_overlay">
  <div class="fullscreen-container js-fullscreen-container">
    <div class="textarea-wrap">
      <textarea name="fullscreen-contents" id="fullscreen-contents" class="js-fullscreen-contents" placeholder="" data-suggester="fullscreen_suggester"></textarea>
          <div class="suggester-container">
              <div class="suggester fullscreen-suggester js-navigation-container" id="fullscreen_suggester"
                 data-url="/niomaster/rolit/suggestions/commit">
              </div>
          </div>
    </div>
  </div>
  <div class="fullscreen-sidebar">
    <a href="#" class="exit-fullscreen js-exit-fullscreen tooltipped leftwards" title="Exit Zen Mode">
      <span class="mega-octicon octicon-screen-normal"></span>
    </a>
    <a href="#" class="theme-switcher js-theme-switcher tooltipped leftwards"
      title="Switch themes">
      <span class="octicon octicon-color-mode"></span>
    </a>
  </div>
</div>



    <div id="ajax-error-message" class="flash flash-error">
      <span class="octicon octicon-alert"></span>
      <a href="#" class="octicon octicon-remove-close close js-ajax-error-dismiss"></a>
      Something went wrong with that request. Please try again.
    </div>

  </body>
</html>

