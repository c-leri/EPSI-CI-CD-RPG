<script lang="ts">
	import type { Game } from '$lib/types/api';
	import { onMount } from 'svelte';
	import { gameApi } from '$lib/Api/CallApi/gameApi';

	let showModal = false;
	let loading = false;
	let error: string | null = null;
	let game: Game | null = null;
	let existingGames: Game[] = [];

	// Statistiques calculées
	$: totalGames = existingGames.length;
	$: activeGames = existingGames.filter((game) => game.pv > 0).length;
	$: finishedGames = existingGames.filter((game) => game.pv === 0).length;
	$: highestRoom = Math.max(...existingGames.map((game) => game.nbSalle), 0);
	$: averagePV = existingGames.length
		? Math.round(existingGames.reduce((sum, game) => sum + game.pv, 0) / existingGames.length)
		: 0;

	function getPVEmoji(pv: number): string {
		if (pv === 0) return '💀';
		if (pv <= 3) return '💔';
		if (pv <= 6) return '❤️';
		return '💖';
	}

	function getRoomEmoji(room: number): string {
		if (room <= 3) return '🏠';
		if (room <= 6) return '🏰';
		if (room <= 9) return '⚔️';
		return '👑';
	}

	// Garder vos fonctions existantes...
	async function handleLoadGame(n: number) {
		showModal = false;
		error = null;
		let loadedGame: Game | null = null;
		try {
			loading = true;
			const loadedGameResponse = await gameApi.loadGame(n);
			loadedGame = loadedGameResponse?.data ?? loadedGameResponse;
			game = loadedGame;
			await loadExistingGames();
		} catch (e) {
			error = e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement du jeu';
		} finally {
			loading = false;
		}
	}

	async function createGame() {
		showModal = false;
		error = null;
		try {
			loading = true;
			const newGameId = await gameApi.createGame();
			const loadedGameResponse = await gameApi.loadGame(newGameId);
			game = loadedGameResponse?.data ?? loadedGameResponse;
			await loadExistingGames();
		} catch (e) {
			error = e instanceof Error ? e.message : 'Une erreur est survenue lors de la création du jeu';
		} finally {
			loading = false;
		}
	}

	async function loadExistingGames() {
		try {
			loading = true;
			const response = await gameApi.getAllGames();
			existingGames = response ?? [];
		} catch (e) {
			console.error('Erreur lors du chargement des jeux:', e);
			error =
				e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement des jeux';
			existingGames = [];
		} finally {
			loading = false;
		}
	}

	async function playNextRoom(n: number) {
		error = null;
		try {
			loading = true;
			const loadedGameResponse = await gameApi.playNextRoom(n);
			game = loadedGameResponse?.data ?? loadedGameResponse;
			await loadExistingGames();
		} catch (e) {
			error = e instanceof Error ? e.message : 'Une erreur est survenue lors du jeu';
		} finally {
			loading = false;
		}
	}

	onMount(() => {
		loadExistingGames();
	});
</script>

<div class="mx-auto max-w-7xl p-4">
	<!-- Stats Cards -->
	<div class="mb-8 grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-4">
		<div class="rounded-lg bg-gray-800 p-6 shadow-lg">
			<h3 class="mb-2 text-gray-400">Total des parties 🎮</h3>
			<p class="text-4xl font-bold text-white">{totalGames}</p>
		</div>
		<div class="rounded-lg bg-gray-800 p-6 shadow-lg">
			<h3 class="mb-2 text-gray-400">Parties actives ⚔️</h3>
			<p class="text-4xl font-bold text-green-500">{activeGames}</p>
		</div>
		<div class="rounded-lg bg-gray-800 p-6 shadow-lg">
			<h3 class="mb-2 text-gray-400">Plus haute salle 🏰</h3>
			<p class="text-4xl font-bold text-yellow-500">{highestRoom}</p>
		</div>
		<div class="rounded-lg bg-gray-800 p-6 shadow-lg">
			<h3 class="mb-2 text-gray-400">PV moyens 💖</h3>
			<p class="text-4xl font-bold text-blue-500">{averagePV}</p>
		</div>
		<div class="rounded-lg bg-gray-800 p-6 shadow-lg">
			<h3 class="mb-2 text-gray-400">Parties fini 🎮</h3>
			<p class="text-4xl font-bold text-blue-500">{finishedGames}</p>
		</div>
	</div>

	<!-- Header avec boutons -->
	<div class="mb-8 flex items-center justify-between">
		<h1 class="text-2xl font-bold">⚔️ Détails du Jeu</h1>
		<div class="flex gap-4">
			<button
				on:click={() => (showModal = true)}
				class="flex items-center gap-2 rounded-lg bg-blue-600 px-4 py-2 text-white transition-colors hover:bg-blue-700"
			>
				📂 Charger une partie
			</button>
			<button
				on:click={createGame}
				class="flex items-center gap-2 rounded-lg bg-green-600 px-4 py-2 text-white transition-colors hover:bg-green-700"
				disabled={loading}
			>
				⭐ Nouvelle partie
			</button>
		</div>
	</div>

	<!-- Messages d'erreur -->
	{#if error}
		<div class="mb-6 rounded-lg border border-red-500 bg-red-900/50 p-4">
			<p class="text-red-300">⚠️ {error}</p>
		</div>
	{/if}

	<!-- État du jeu -->
	{#if loading}
		<div class="flex items-center justify-center p-8">
			<div class="animate-bounce text-4xl">⚔️</div>
		</div>
	{:else if game}
		<div class="mb-6 rounded-lg bg-gray-800 p-6 shadow-lg">
			<div class="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-4">
				<div class="rounded-lg bg-gray-700 p-4">
					<h3 class="text-gray-400">🎮 Partie</h3>
					<p class="text-2xl font-bold text-white">#{game.id}</p>
				</div>
				<div class="rounded-lg bg-gray-700 p-4">
					<h3 class="text-gray-400">Points de vie</h3>
					<p class="text-2xl font-bold {game.pv === 0 ? 'text-red-500' : 'text-green-500'}">
						{getPVEmoji(game.pv)}
						{game.pv} PV
					</p>
				</div>
				<div class="rounded-lg bg-gray-700 p-4">
					<h3 class="text-gray-400">Salle actuelle</h3>
					<p class="text-2xl font-bold text-yellow-500">
						{getRoomEmoji(game.nbSalle)} #{game.nbSalle}
					</p>
				</div>
				{#if game.lastDiceRoll !== 7}
					<div class="rounded-lg bg-gray-700 p-4">
						<h3 class="text-gray-400">Dernier lancer</h3>
						<p class="text-2xl font-bold text-blue-500">
							{game.lastDiceRoll === 6 ? '🎯' : '🎲'}
							{game.lastDiceRoll}
						</p>
					</div>
				{/if}
			</div>

			{#if game.pv === 0}
				<div class="mt-6 rounded-lg border border-red-500 bg-red-900/50 p-4">
					<p class="text-red-300">
						💀 Vous êtes mort. Veuillez démarrer une nouvelle partie ou charger une autre
						sauvegarde.
					</p>
				</div>
			{:else}
				<button
					class="mt-6 flex items-center gap-2 rounded-lg bg-yellow-600 px-4 py-2 text-white transition-colors hover:bg-yellow-700"
					on:click={() => playNextRoom(game!.id)}
				>
					🚪 Salle suivante
				</button>
			{/if}
		</div>
	{/if}

	<!-- Modal de chargement des parties -->
	{#if showModal}
		<div class="bg-opacity-40 fixed inset-0 z-50 flex items-center justify-center bg-black">
			<div class="min-w-[300px] rounded-lg bg-gray-800 p-6 shadow-lg">
				<div class="mb-4 flex items-center justify-between">
					<h2 class="text-lg font-bold text-white">🎮 Charger une partie</h2>
					<button class="text-gray-400 hover:text-gray-200" on:click={loadExistingGames}>
						🔄
					</button>
				</div>

				{#if loading}
					<div class="flex items-center justify-center p-4">
						<div class="animate-bounce text-3xl">⚔️</div>
					</div>
				{:else if existingGames.length > 0}
					<div class="mb-4 flex max-h-[60vh] flex-col gap-2 overflow-y-auto">
						{#each existingGames as game (game.id)}
							<button
								class="flex items-center justify-between rounded bg-gray-700 px-4 py-3 text-white transition-colors hover:bg-gray-600"
								on:click={() => handleLoadGame(game.id)}
							>
								<span>🎮 Partie #{game.id}</span>
								<span class="flex items-center gap-2">
									{#if game.pv === 0}
										<span class="text-red-400">💀 Game Over</span>
									{:else}
										<span class="text-green-400">{getPVEmoji(game.pv)} {game.pv} PV</span>
										<span class="text-yellow-400"
											>{getRoomEmoji(game.nbSalle)} Salle {game.nbSalle}</span
										>
									{/if}
								</span>
							</button>
						{/each}
					</div>
				{:else}
					<div class="py-8 text-center text-gray-400">
						<p class="mb-2 text-4xl">🎲</p>
						<p>Aucune partie sauvegardée</p>
						<p class="mt-2 text-sm">Créez une nouvelle partie pour commencer ⭐</p>
					</div>
				{/if}

				<div class="mt-4 flex justify-end gap-2">
					<button
						class="rounded bg-gray-600 px-4 py-2 text-white transition-colors hover:bg-gray-500"
						on:click={() => (showModal = false)}
					>
						❌ Fermer
					</button>
				</div>
			</div>
		</div>
	{/if}
</div>
