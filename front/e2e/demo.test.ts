import { expect, test } from '@playwright/test';

test('home page has expected h1', async ({ page }) => {
	await page.goto('/');
	await expect(page.locator('h1').first()).toBeVisible();
});

test('Click "Charger une sauvegarde" shows modal with correct title ', async ({ page }) => {
	await page.goto('/');

	await page.getByRole('button', { name: /📂 Charger une partie/i }).click();

	await expect(page.getByText(/🎮 Charger une partie/i)).toBeVisible();
});

test('Click "annuler" closes modal and removes title ', async ({ page }) => {
	await page.goto('/');

	await page.getByRole('button', { name: /📂 Charger une partie/i }).click();

	await expect(page.getByText(/🎮 Charger une partie/i)).toBeVisible();

	await page.getByRole('button', { name: /❌ Fermer/i }).click();

	// Verify the modal title is gone
	await expect(page.getByText(/🎮 Charger une partie/i)).toBeHidden();
});
