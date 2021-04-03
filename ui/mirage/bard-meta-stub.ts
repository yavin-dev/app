/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */

//metadata
import type { Server } from 'miragejs';
import { Response } from 'miragejs';
import dimModels from './fixtures/metadata-dimensions';
import metricModels from './fixtures/metadata-metrics';
import tableModels from './fixtures/metadata-tables';
import timeGrainModels from './fixtures/metadata-time-grains';

/**
 * Method to configure metadata endpoints
 */
export default function (this: Server): void {
  /**
   * unsupported metricFunctions endpoint
   */
  this.get(
    'metricFunctions',
    () =>
      new Response(404, { 'Content-Type': 'text/plain' }, 'Resource Not Found')
  );
  this.get(
    'metricFunctions/:id',
    () =>
      new Response(404, { 'Content-Type': 'text/plain' }, 'Resource Not Found')
  );

  /**
   * /tables endpoint
   */
  this.get('/tables', (_db, req) => {
    let tables = tableModels;

    if (req.queryParams.format === 'fullview') {
      tables = tables.map((table) => {
        let timeGrains = timeGrainModels.map((timeGrain) => {
          return Object.assign(
            {},
            timeGrain,
            { metrics: metricModels },
            { dimensions: dimModels }
          );
        });

        return Object.assign({}, table, { timeGrains });
      });
    }
    return { tables };
  });
}
