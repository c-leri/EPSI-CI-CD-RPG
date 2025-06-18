<script lang="ts">
	import { roomApi } from '../Api/CallApi/GetGame';
	import type { Game } from '$lib/types/api';
	import { onMount } from 'svelte';
	import { gameApi } from '$lib/Api/CallApi/gameApi';

	let showModal = false;
	let loading = false;
	let error: string | null = null;
	let game: Game | null = null;
	let existingGames: Game[] = [];

	async function handleLoadGame(n: number) {
		showModal = false;
		error = null;
		let loadedGame: Game | null = null;
		try {
			loading = true;
			const loadedGameResponse = await gameApi.loadGame(n);
			loadedGame = loadedGameResponse?.data ?? loadedGameResponse;

			game = loadedGame;
		} catch (e) {
			error = e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement du jeu';
		} finally {
			loading = false;
		}
		console.log('Game loaded:', loadedGame);
		console.log('Game loaded:', game);
	}

	async function createGame() {
		showModal = false;
		error = null;
		let loadedGame: Game | null = null;
		let newGameId: number = 0;
		try {
			loading = true;
			newGameId = await gameApi.createGame();
			const loadedGameResponse = await gameApi.loadGame(newGameId);
			loadedGame = loadedGameResponse?.data ?? loadedGameResponse;
			game = loadedGame;
		} catch (e) {
			error = e instanceof Error ? e.message : 'Une erreur est survenue lors de la création du jeu';
		} finally {
			loading = false;
		}
		console.log('game id', newGameId);
		console.log('Game created:', loadedGame);
		console.log('Game state:', game);
	}

	async function loadExistingGames() {
		try {
			loading = true;
			const response = await gameApi.getAllGames();
			console.log('response loadExistingGames', response);
			existingGames = response ?? [];
			console.log('response loadExistingGames', existingGames);
		} catch (e) {
			console.error('Erreur lors du chargement des jeux:', e);
			error =
				e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement des jeux';
		} finally {
			loading = false;
		}
	}

	async function playNextRoom(n: number) {
		showModal = false;
		error = null;
		let loadedGame: Game | null = null;
		try {
			loading = true;
			const loadedGameResponse = await gameApi.playNextRoom(n);
			loadedGame = loadedGameResponse?.data ?? loadedGameResponse;
			game = loadedGame;
		} catch (e) {
			error = e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement du jeu';
		} finally {
			loading = false;
		}
		console.log('play next room:', loadedGame);
		console.log('play next room:', game);
	}

	async function fetchGame() {
		loading = true;
		error = null;

		try {
			const gameId = localStorage.getItem('gameId');
			if (!gameId) {
				throw new Error('ID du jeu non trouvé');
			}

			const storedGame = localStorage.getItem('game');
			if (storedGame) {
				game = JSON.parse(storedGame);
			} else {
				const response = await roomApi.getGame(gameId);
				game = response.data;
				localStorage.setItem('game', JSON.stringify(game));
			}
		} catch (e) {
			error = e instanceof Error ? e.message : 'Une erreur est survenue';
		} finally {
			loading = false;
		}
	}

	onMount(() => {
		fetchGame();
	});
</script>

<div class="mx-auto max-w-2xl p-4">
	<div class="mb-6 flex items-center justify-between">
		<h1 class="text-2xl font-bold">Détails du Jeu</h1>
		<button
			onclick={() => (showModal = true)}
			class="flex items-center gap-2 rounded-lg bg-gradient-to-r from-blue-500 to-blue-700 px-4 py-2 text-white shadow transition-all duration-200 hover:from-blue-600 hover:to-blue-800 focus:ring-2 focus:ring-blue-400 focus:outline-none"
		>
			<svg
				xmlns="http://www.w3.org/2000/svg"
				class="h-5 w-5"
				fill="none"
				viewBox="0 0 24 24"
				stroke="currentColor"
			>
				<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
			</svg>
			Charger une sauvegarde
		</button>

		<script lang="ts">
			let existingGames: Game[] = [];

			async function loadExistingGames() {
				try {
					loading = true;
					const response = await gameApi.getAllGames();
					existingGames = response?.data ?? [];
				} catch (e) {
					console.error('Erreur lors du chargement des jeux:', e);
					error =
						e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement des jeux';
					existingGames = [];
				} finally {
					loading = false;
				}
			}

			onMount(async () => {
				await loadExistingGames();
			});
		</script>

		{#if showModal}
			<div class="bg-opacity-40 fixed inset-0 z-50 flex items-center justify-center bg-black">
				<div class="min-w-[300px] rounded-lg bg-black p-6 shadow-lg">
					<div class="mb-4 flex items-center justify-between">
						<h2 class="text-lg font-bold">Choisiser votre partie</h2>
						<button class="text-gray-400 hover:text-gray-200" onclick={() => loadExistingGames()}>
							<svg
								xmlns="http://www.w3.org/2000/svg"
								class="h-5 w-5"
								viewBox="0 0 20 20"
								fill="currentColor"
							>
								<path
									fill-rule="evenodd"
									d="M4 2a1 1 0 011 1v2.101a7.002 7.002 0 0111.601 2.566 1 1 0 11-1.885.666A5.002 5.002 0 005.999 7H9a1 1 0 010 2H4a1 1 0 01-1-1V3a1 1 0 011-1zm.008 9.057a1 1 0 011.276.61A5.002 5.002 0 0014.001 13H11a1 1 0 110-2h5a1 1 0 011 1v5a1 1 0 11-2 0v-2.101a7.002 7.002 0 01-11.601-2.566 1 1 0 01.61-1.276z"
									clip-rule="evenodd"
								/>
							</svg>
						</button>
					</div>

					{#if loading}
						<div class="flex items-center justify-center p-4">
							<div
								class="h-5 w-5 animate-spin rounded-full border-2 border-blue-500 border-t-transparent"
							></div>
						</div>
					{:else if existingGames.length > 0}
						<div class="mb-4 flex max-h-[60vh] flex-col gap-2 overflow-y-auto">
							{#each existingGames as game (game.id)}
								<button
									class="flex items-center justify-between rounded bg-blue-600 px-3 py-2 text-white hover:bg-blue-700"
									onclick={() => handleLoadGame(game.id)}
								>
									<span>Partie {game.id}</span>
									<span class="text-sm">
										{#if game.pv === 0}
											<span class="text-red-300">Mort</span>
										{:else}
											PV: {game.pv} | Salle: {game.nbSalle}
										{/if}
									</span>
								</button>
							{/each}
						</div>
					{:else}
						<p class="mb-4 text-center text-gray-400">
							Aucune partie sauvegardée length: {existingGames.length}
						</p>
					{/if}

					<div class="mt-4 flex justify-end gap-2">
						<button
							class="rounded bg-gray-300 px-3 py-1 hover:bg-gray-400"
							onclick={() => (showModal = false)}
						>
							Fermer
						</button>
					</div>
				</div>
			</div>
		{/if}

		<button
			onclick={createGame}
			class="mr-2 flex items-center gap-2 rounded-lg bg-gradient-to-r from-green-500 to-green-700 px-4 py-2 text-white shadow transition-all duration-200 hover:from-green-600 hover:to-green-800 focus:ring-2 focus:ring-green-400 focus:outline-none"
			disabled={loading}
		>
			<svg
				xmlns="http://www.w3.org/2000/svg"
				class="h-5 w-5"
				fill="none"
				viewBox="0 0 24 24"
				stroke="currentColor"
			>
				<path
					stroke-linecap="round"
					stroke-linejoin="round"
					stroke-width="2"
					d="M12 6v6m0 0v6m0-6h6m-6 0H6"
				/>
			</svg>
			Nouvelle Partie
		</button>
	</div>

	{#if error}
		<div
			class="relative mb-4 rounded border border-red-400 bg-red-100 px-4 py-3 text-red-700"
			role="alert"
		>
			<span class="block sm:inline">{error}</span>
		</div>
	{/if}

	{#if loading}
		<div class="flex items-center justify-center p-8">
			<div class="animate-pulse text-gray-600">Chargement...</div>
		</div>
	{:else if game && !error}
		{#if game.pv === 0}
			<div
				class="relative mb-4 rounded border border-red-400 bg-red-100 px-4 py-3 text-red-700"
				role="alert"
			>
				Vous êtes mort. Veuillez démarrer une nouvelle partie ou charger une autre sauvegarde.
			</div>
		{:else}
			<button
				class="rounded bg-blue-600 px-3 py-1 text-white hover:bg-blue-700"
				onclick={() => playNextRoom(game!.id)}
			>
				next room
			</button>
		{/if}
		!
		<div class="rounded-lg bg-white p-6 shadow-lg">
			<div class="rounded-lg border bg-gray-50 p-4">
				<h3 class="font-medium text-black">Jeu {game.id}</h3>
				<h3 class="font-medium text-black">PV: {game.pv}</h3>
				<h3 class="font-medium text-black">nbSalle: {game.nbSalle}</h3>

				{#if game.lastDiceRoll !== 7}
					<h3 class="font-medium text-black">lastDiceRoll: {game.lastDiceRoll}</h3>
				{/if}
			</div>
		</div>
	{/if}
</div>
