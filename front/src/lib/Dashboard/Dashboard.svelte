<script lang="ts">
	import { Home, Sun, Moon } from '@lucide/svelte';
	import GameViewer from '$lib/components/GameViewer.svelte';

	let isDark = $state(false);

	function toggleMode() {
		isDark = !isDark;
		document.documentElement.classList.toggle('dark');
	}
</script>

<div class="bg-background min-h-screen">
	<!-- Header -->
	<header class="bg-background/95 fixed top-0 z-50 w-full border-b backdrop-blur">
		<div class="flex h-16 items-center px-4">
			<div class="flex flex-1 items-center justify-between">
				<h2 class="text-lg font-semibold">🎲 Jeux de rôle</h2>
				<div class="flex items-center gap-4">
					<button
						class="hover:bg-muted rounded-full p-2"
						onclick={toggleMode}
						aria-label="Basculer le thème"
					>
						{#if isDark}
							<Sun class="h-5 w-5" />
						{:else}
							<Moon class="h-5 w-5" />
						{/if}
					</button>
					<div class="bg-muted h-8 w-8 rounded-full"></div>
				</div>
			</div>
		</div>
	</header>

	<!-- Sidebar -->
	<aside class="bg-sidebar fixed top-0 left-0 z-40 mt-16 h-[calc(100vh-4rem)] w-64 border-r">
		<nav class="space-y-1 p-4">
			<a
				href="/dashboard"
				class="bg-sidebar-accent text-sidebar-accent-foreground flex items-center gap-3 rounded-lg px-3 py-2"
			>
				<Home class="h-5 w-5" />
				🎮 Game
			</a>
		</nav>
	</aside>

	<!-- Contenu principal -->
	<main class="mt-16 ml-64 min-h-[calc(100vh-4rem)] p-8">
		<GameViewer />
	</main>
</div>
