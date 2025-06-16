<script lang="ts">
    import { Home, Users, Settings, BarChart3, Bell, Sun,Moon } from '@lucide/svelte';
    import "../../app.css";
    import { ModeWatcher } from "mode-watcher";
    let { children } = $props();

    let isDark = false;

    function toggleMode() {
        isDark = !isDark;
        document.documentElement.classList.toggle('dark');
    }

</script>



<div class="min-h-screen bg-background">
    <ModeWatcher />
    {@render children?.()}
    <header class="fixed top-0 z-50 w-full border-b bg-background/95 backdrop-blur">
        <div class="flex h-16 items-center px-4">
            <div class="flex flex-1 items-center justify-between">
                <h2 class="text-lg font-semibold">Tableau de Bord</h2>

                <div class="flex items-center gap-4">
                    <button
                            class="rounded-full p-2 hover:bg-muted"
                            on:click={toggleMode}
                            aria-label="Basculer le thème"
                    >
                        {#if isDark}
                            <Sun class="h-5 w-5" />
                        {:else}
                            <Moon class="h-5 w-5" />
                        {/if}
                    </button>
                    <button class="rounded-full p-2 hover:bg-muted">
                        <Bell class="h-5 w-5" />
                    </button>
                    <div class="h-8 w-8 rounded-full bg-muted" />
                </div>
            </div>
        </div>
    </header>


    <!-- Barre latérale -->
    <aside class="fixed left-0 top-0 z-40 mt-16 h-[calc(100vh-4rem)] w-64 border-r bg-sidebar">
        <nav class="space-y-1 p-4">
            <a href="/dashboard" class="flex items-center gap-3 rounded-lg bg-sidebar-accent px-3 py-2 text-sidebar-accent-foreground">
                <Home class="h-5 w-5" />
                Accueil
            </a>
            <a href="/dashboard/users" class="flex items-center gap-3 rounded-lg px-3 py-2 text-sidebar-foreground hover:bg-sidebar-accent/50">
                <Users class="h-5 w-5" />
                Utilisateurs
            </a>
            <a href="/dashboard/analytics" class="flex items-center gap-3 rounded-lg px-3 py-2 text-sidebar-foreground hover:bg-sidebar-accent/50">
                <BarChart3 class="h-5 w-5" />
                Analytique
            </a>
            <a href="/dashboard/settings" class="flex items-center gap-3 rounded-lg px-3 py-2 text-sidebar-foreground hover:bg-sidebar-accent/50">
                <Settings class="h-5 w-5" />
                Paramètres
            </a>
        </nav>
    </aside>

    <!-- Contenu principal -->
    <main class="ml-64 mt-16 min-h-[calc(100vh-4rem)] p-8">
        <div class="grid gap-4 md:grid-cols-2 lg:grid-cols-4">

            <!-- Cartes statistiques -->
            <div class="rounded-lg border bg-card p-6">
                <h3 class="text-sm font-medium text-muted-foreground">Lorem ipsum dolor sit amet consectetur adipisicing elit. Sint, excepturi, aliquam fugiat modi perferendis fuga tenetur possimus qui aspernatur necessitatibus alias quia obcaecati! Facere ullam tempore hic neque perferendis a?</h3>
                <p class="mt-2 text-2xl font-bold"></p>
            </div>

            <div class="rounded-lg border bg-card p-6">
                <h3 class="text-sm font-medium text-muted-foreground">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex ducimus omnis quo et itaque facere fugit, iure molestiae. Recusandae sunt dolor, illum iusto quo a obcaecati officiis nulla ratione architecto.</h3>
                <p class="mt-2 text-2xl font-bold"></p>
            </div>

            <div class="rounded-lg border bg-card p-6">
                <h3 class="text-sm font-medium text-muted-foreground">Lorem ipsum dolor, sit amet consectetur adipisicing elit. Odit voluptas officiis, dignissimos iure, explicabo placeat totam iusto doloremque minima quos quis labore, autem ex nostrum provident dolor unde libero officia.</h3>
                <p class="mt-2 text-2xl font-bold"></p>
            </div>

            <div class="rounded-lg border bg-card p-6">
                <h3 class="text-sm font-medium text-muted-foreground">Lorem ipsum dolor sit amet consectetur adipisicing elit. Veniam nemo facilis, aliquam quia sit voluptatem suscipit minus quaerat ullam, praesentium velit unde quos eos excepturi dicta totam! Consequuntur, maxime dolor.</h3>
                <p class="mt-2 text-2xl font-bold"></p>
            </div>
        </div>

        <!-- Section de contenu supplémentaire -->
        <div class="mt-8 rounded-lg border bg-card p-6">
            <h3 class="text-lg font-medium">Activité Récente</h3>
            <p class="mt-2 text-muted-foreground">Aucune activité récente à afficher.</p>
        </div>
    </main>
</div>